package com.thirdparty.eweixin;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.javapai.framework.utils.UtilHttpRst;

public class DeptInfoClient {
	public String listDept(String token, String deptId) {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/simplelist?access_token=";
		if (StringUtils.isBlank(deptId)) {
			return UtilHttpRst.requestGet(url + token);
		} else {
			return UtilHttpRst.requestGet(url + token + "&id=" + deptId);
		}
	}

	public String getDept(String token, String deptId) {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/get";
		return UtilHttpRst.requestGet(url, Map.of("access_token", token, "id", deptId));
	}

}
