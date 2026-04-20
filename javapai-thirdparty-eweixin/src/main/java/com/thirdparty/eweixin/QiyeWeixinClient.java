package com.thirdparty.eweixin;

import com.javapai.framework.utils.UtilHttpRst;

public class QiyeWeixinClient {
//	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public String getAccessToken(String corpId, String appSecret) {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpId + "&corpsecret=" + appSecret;
		return UtilHttpRst.requestGet(url);

	}
}
