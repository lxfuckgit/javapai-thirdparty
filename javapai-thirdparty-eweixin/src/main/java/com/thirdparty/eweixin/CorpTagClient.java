package com.thirdparty.eweixin;

import com.javapai.framework.utils.UtilHttpRst;

public class CorpTagClient {
	public String listCorpTag(String token) {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get_corp_tag_list?access_token=";
		return UtilHttpRst.requestGet(url + token);
	}

}
