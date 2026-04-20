package com.thirdparty.eweixin;

import java.util.List;
import java.util.Map;

import com.javapai.framework.utils.UtilHttpRst;
import com.javapai.framework.utils.UtilJson;
import com.thirdparty.params.EweixinResult;

/**
 * 企业微信-[客户联系]-[获客链接]。<br>
 * <br>
 * 
 * <strong>官方链接：</strong>https://developer.work.weixin.qq.com/document/path/97297
 */
public class CustAcquisitionClient {
	
	/**
	 * 获客链接的列表。<br>
	 * 
	 * @return
	 */
	public String listLink(String token) {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/customer_acquisition/list_link?access_token=";
		Map<String, Object> params = Map.of("limit", 100);
		return UtilHttpRst.requestPost(url + token, params);
	}
	
	/**
	 * 获客链接的创建<br>
	 * 
	 * @param token     （必填）
	 * @param linkName  （必填）
	 * @param staffList （必填）
	 * @return
	 */
	public String createLink(String token, String linkName, List<String> staffList) {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/customer_acquisition/create_link?access_token=" + token;
		Map<String, Object> params = Map.of("link_name", linkName, "range", Map.of("user_list", staffList));
		return UtilHttpRst.requestPost(url, params);
	}
	
	/**
	 * 获客链接的创建<br>
	 * 
	 * @param token     （必填）
	 * @param linkName  （必填）
	 * @param staffList （必填）
	 * @return
	 */
	public String getLink(String token, String linkName, List<String> staffList) {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/customer_acquisition/create_link?access_token=" + token;
		Map<String, Object> params = Map.of("link_name", linkName, "range", Map.of("user_list", staffList));
		return UtilHttpRst.requestPost(url, params);
	}
	
	/**
	 * 获客链接的创建<br>
	 * 
	 * @param token     （必填）
	 * @param linkName  （必填）
	 * @param staffList （必填）
	 * @return
	 */
	public String updateLink(String token, String linkName, List<String> staffList) {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/customer_acquisition/create_link?access_token=" + token;
		Map<String, Object> params = Map.of("link_name", linkName, "range", Map.of("user_list", staffList));
		return UtilHttpRst.requestPost(url, params);
	}

	/**
	 * 获客链接的删除<br>
	 * 
	 * @param token  （必填）
	 * @param linkId （必填）
	 * @return
	 */
	public EweixinResult deleteLink(String token, String linkId) {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/customer_acquisition/delete_link?access_token=" + token;
		String result = UtilHttpRst.requestPost(url, Map.of("link_id", linkId));
		return UtilJson.json2Object(result, EweixinResult.class);
	}

}
