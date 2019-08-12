package com.javapai.thirdparty.haiguan;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.http.Consts;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.javapai.thirdparty.haiguan.vo.HgCheckVO;

@RestController
public class HaiguanController {
	private static final Logger logger = LoggerFactory.getLogger(HaiguanController.class);
	
	@org.springframework.beans.factory.annotation.Autowired
	HaiguanSign haiguanSign;
	
	@org.springframework.beans.factory.annotation.Autowired
	RestTemplate restTempalte;

	@Value(value = "${haiguan.certNo}")
	private String certNo;
	@Value(value = "${haiguan.password}")
	private String password;
	@Value(value = "${haiguan.uploadUrl}")
	private String uploadUrl;
	
	@PostMapping("/haiguan/repDataApply")
	public Map<String,HgCheckVO> repDataApply(@RequestBody HgCheckVO entity) {
		logger.info("------>{}", JSON.toJSONString(entity));
		
		/*加签成海关指定数据格式(4项:不能多不能少)*/
		StringBuffer sb = new StringBuffer();
		sb.append("\"sessionID\":\"").append(entity.getSessionID()).append("\"").append("||");
		String headerString = JSON.toJSONString(entity.getPayExchangeInfoHead(),SerializerFeature.SortField);
		sb.append("\"payExchangeInfoHead\":\"").append(headerString).append("\"").append("||");
		String listString = JSON.toJSONString(entity.getPayExchangeInfoLists(),SerializerFeature.SortField);
		sb.append("\"payExchangeInfoLists\":\"").append(listString).append("\"").append("||");
		sb.append("\"serviceTime\":\"").append(entity.getServiceTime()).append("\"");
		String signValue = sb.toString();//.substring(0, sb.toString().length() - 2);
		System.out.println("加签参数拼接->" + signValue);
		
		CompletableFuture.supplyAsync(() -> {
			/**/
			Map<String,String> args = new java.util.HashMap<>();
			args.put("inData", signValue);
			args.put("passwd", password);
			JSONObject epdata = new JSONObject(true);
			epdata.put("_method", "cus-sec_SpcSignDataAsPEM");
			epdata.put("_id", 1);
			epdata.put("args", args);
			haiguanSign.sign(epdata.toJSONString(), new Sign179Callback() {
				@Override
				public void signReply(String reply) {
					// TODO Auto-generated method stub
					if (!StringUtils.isEmpty(reply)) {
						logger.info("-----sessionId:{}, sign:{}", entity.getSessionID(), reply);
						entity.setCertNo(certNo);
						entity.setSignValue(reply);
					}
				}
			});
			
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			return entity.getSignValue();
		}).thenAccept(singValue -> {
			System.out.println("海关179数据上报参数:payExInfoStr=" + JSON.toJSONString(entity,SerializerFeature.SortField));
			List<NameValuePair> param = new ArrayList<NameValuePair>();
			param.add(new BasicNameValuePair("payExInfoStr", JSON.toJSONString(entity,SerializerFeature.SortField)));
			CloseableHttpClient client = getHttpsClient();
			HttpPost post = new HttpPost(uploadUrl);
			post.setEntity(new UrlEncodedFormEntity(param,Consts.UTF_8));
			try {
				CloseableHttpResponse xx = client.execute(post);
				if(HttpStatus.SC_OK == xx.getStatusLine().getStatusCode()) {
					String result = EntityUtils.toString(xx.getEntity(), Consts.UTF_8);
					System.out.println("---海关179数据上报结果：" + result);
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		return null;
	}
	
	private static CloseableHttpClient getHttpsClient(){
		SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            
            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                @Override
                public void verify(String host, SSLSocket ssl) throws IOException {
                }

                @Override
                public void verify(String host, X509Certificate cert) throws SSLException {
                }

                @Override
                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                }
            });
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}

}
