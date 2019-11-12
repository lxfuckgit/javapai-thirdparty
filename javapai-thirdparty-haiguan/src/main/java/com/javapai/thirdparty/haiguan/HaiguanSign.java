package com.javapai.thirdparty.haiguan;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class HaiguanSign {
	private static final Logger LOGGER = LoggerFactory.getLogger(HaiguanSign.class);
	
	/**
	 * 超时时间
	 */
	public static final Integer TIME_OUT = 3 * 60 * 1000;
	/**
	 * 时间步长
	 */
	public static final Integer TIME_STEP = 10;
	
	@Value(value = "${haiguan.sign.wsUrl}")
	private String wsUrl;
	
	/**
	 * 保存WebSocket链接
	 */
	private Map<String, WebSocketClient> clientPool = new HashMap<>();
	
	/**
	 * 海关数据加签。<br>
	 * 
	 * @param value
	 *            待加签后的值.<br>
	 * @return signValue加签后的值.<br>
	 */
	public String sign(String value,Sign179Callback callback) {
		System.out.println("请求加签报文->" + value);
		
		/**/
		WebSocketClient client = clientPool.get(wsUrl);
		if (client != null && client.isConnecting() && client.isOpen()) {
			client.send(value);
		} else {
			try {
				client = new WebSocketClient(new URI(wsUrl)) {
					@Override
					public void onOpen(ServerHandshake shake) {
						// TODO Auto-generated method stub
						System.out.println("握手...");
						for (Iterator<String> it = shake.iterateHttpFields(); it.hasNext();) {
							String key = it.next();
							System.out.println(key + "->" + shake.getFieldValue(key));
						}
					}

					@Override
					public void onMessage(String message) {
						// TODO Auto-generated method stub
						if (!StringUtils.isEmpty(message)) {
							System.out.println("接收到消息：" + message);
							JSONObject jsonObject = JSON.parseObject(String.valueOf(message));
							Integer id = jsonObject.getInteger("_id");
							if (id != null && id == 0) {
								System.out.println("根据海关ws报文，第一次回复为握手通知,并非'加签'信息!");//第一次(id=0)
							} else if (id != null && id == 1) {
								JSONArray data = jsonObject.getJSONObject("_args").getJSONArray("Data");
								String signValue = data.getString(0);
								callback.signReply(signValue);
							}
						}
					}

					@Override
					public void onClose(int code, String reason, boolean remote) {
						// TODO Auto-generated method stub
						System.out.println("-----------close()");
					}

					@Override
					public void onError(Exception ex) {
						// TODO Auto-generated method stub
						System.out.println("-----------onError:"+ex.getMessage());
					}
				};
			} catch (URISyntaxException e) {
				e.printStackTrace();
				System.out.println("--------->websocket client exception.");
			}
			
			client.connect();
			Integer count = 0;
			// 阻塞等待OPEN
			while (!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
				try {
					count += TIME_STEP;
					Thread.sleep(TIME_STEP);
					if (count >= TIME_OUT) {
						LOGGER.info("error-{}", new Exception("WS服务器连接超时！或服务器已经关闭"));
						break;
					}
				} catch (InterruptedException e) {
					LOGGER.error("发送信息异常-{}", e);
				}
			}

			client.send(value);
			// 放入缓存
			clientPool.put(wsUrl, client);
		}
		
		return "";
	}
	
}