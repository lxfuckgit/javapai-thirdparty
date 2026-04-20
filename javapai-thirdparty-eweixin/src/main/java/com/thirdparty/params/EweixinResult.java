package com.thirdparty.params;

/**
 * 企业微信API接口的响应报文。
 */
public class EweixinResult {
	private static final Integer RETURN_CODE = 0;
	
	private Integer errcode;
	private String errmsg;

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	public boolean ifSuccess() {
		return RETURN_CODE == this.errcode ? true : false;
	}

}
