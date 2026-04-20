package com.thirdparty.eweixin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javapai.framework.utils.UtilHttpRst;

/**
 * 企业微信-群聊功能。<br>
 * <br>
 * 
 * <strong>官方链接：</strong>https://developer.work.weixin.qq.com/document/path/90244
 */
public class GroupChatClient {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 创建群聊<br>
	 * 
	 * @param token      授权令牌（必填）
	 * @param groupId    组标识（非必填）
	 * @param groupName  组名称（必填）
	 * @param groupOwner 组管理员（非必填）
	 * @param userList   组成员（必填）
	 * @return
	 */
	public String createGroupChat(String token, String groupId, String groupName, String groupOwner, List<String> userList) {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/appchat/create?access_token=";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", groupName);
		params.put("userlist", userList);
		if (StringUtils.isNotBlank(groupOwner)) {
			params.put("owner", groupOwner);
		}
		if (StringUtils.isNotBlank(groupId)) {
			params.put("chatid", groupId);
		}
		return UtilHttpRst.requestPost(url + token, params);
	}

	/**
	 * 
	 * @param toUser
	 * @param toParty
	 * @param content
	 * @return
	 */
//	private String updateGroupChat(String toUser, String toParty, Object content) {
//		String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=";
//		Map<String, Object> param = Map.of("msgtype", "text", "agentid", appId, "safe", 1, "touser", toUser, "toparty",
//				toParty, "text", content);
//		String result = UtilHttpRst.requestPost(url + getAccessToken(), param);
//		logger.info("--->企微内部消息：{}", result);
//		return result;
//	}

	/**
	 * 
	 * @param token   授权令牌（必填）
	 * @param groupId 组标识（必填）
	 * @return
	 */
	public String getGroupChat(String token, String groupId) {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=";
		Map<String, String> param = Map.of("access_token", token, "chatid", groupId);
		return UtilHttpRst.requestGet(url, param);
	}

	/**
	 * 
	 * @param token
	 * @param groupId
	 * @param content
	 * @param msgtype
	 * @param userList
	 * @return
	 */
	public String sendToGruopChat(String token, String groupId, String content, String msgtype, List<String> userList) {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/appchat/send?access_token=";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("chatid", groupId);
		params.put("safe", 0);
		if ("text".equals(msgtype)) {
			params.put("msgtype", "text");
			params.put("text", Map.of("content", content, "mentioned_list", userList));
			return UtilHttpRst.requestPost(url + token, params);
		} else {
			logger.info("--->暂不支持此类型({})的内容发送！", msgtype);
			return null;
		}
	}
}
