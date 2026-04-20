package com.thirdparty.eweixin;

import java.util.Map;

import com.javapai.framework.utils.UtilHttpRst;

public class UserInfoClient {

	/**
	 * 通过手机号获取其所对应的userId<br>
	 * 
	 * @param token 
	 * @param mobile
	 * @return
	 */
	public String getUserIdByMobile(String token, String mobile) {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserid?access_token=";
		return UtilHttpRst.requestPost(url + token, Map.of("mobile", mobile));
	}

//	/**
//	 * 通过手机号获取其所对应的userInfo<br>
//	 * 
//	 * @param toUser
//	 * @param toParty
//	 * @param content
//	 * @return
//	 */
//	public String getUserInfoByMobile(String token, String mobile) {
//		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserid?access_token=";
//		return UtilHttpRst.requestPost(url + token, Map.of("mobile", mobile));
//	}

	/**
	 * 企业成员详情。<br>
	 * 通过用户成员标识获取其所对应的userInfo<br>
	 * 
	 * @param token
	 * @param userId
	 * @return
	 */
	public String getUserInfoByUserId(String token, String userId) {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=" + token + "&userid=" + userId;
		return UtilHttpRst.requestGet(url);
	}
	
	/**
	 * 企业部门成员列表。<br>
	 * 
	 * @param token  访问令牌（必填）
	 * @param deptId 部门标识（必填）
	 * @return
	 */
	public String listUserInfoByDeptId(String token, String deptId) {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=" + token + "&department_id=" + deptId;
		return UtilHttpRst.requestGet(url);
	}

	/**
	 * 查询具有【客户联系】功能的成员（标识）列表。<br>
	 * <br>
	 * <strong>提示：</strong>此接口只能获取配置了[客户联系]功能的成员列表。<br>
	 * 
	 * @param token
	 * @return
	 */
	public String listFollowUserId(String token) {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get_follow_user_list?access_token=";
		return UtilHttpRst.requestGet(url + token);
	}

	/**
	 * 查询具有【客户联系】功能的成员（明细）列表。<br>
	 * <br>
	 * <strong>提示：</strong>此接口只能获取配置了[客户联系]功能的成员列表。<br>
	 * 
	 * @param token
	 * @return
	 */
//	public List<String> listFollowUserInfo(String token) {
//		String result = listFollowUserId(token);
//		Map<String, String> mapResult = UtilJson.string2Map(result);
//		if ("".equals(mapResult.get("errcode"))) {
//
//		}
//		return null;
//	}

}
