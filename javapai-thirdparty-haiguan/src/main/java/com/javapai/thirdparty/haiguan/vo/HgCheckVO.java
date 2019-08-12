package com.javapai.thirdparty.haiguan.vo;

import com.alibaba.fastjson.annotation.JSONType;

/**
 * 海关抽样数据格式对象.<br>
 * <br>
 * 对象根据海关文档<海关跨境电商进口统一版信息化系统平台数据实时获取接口（试行）.doc>第1.6.1接口中payExInfoStr参数格式定义。<br>
 * 
 * @author pooja
 *
 */
@JSONType(orders = { "sessionID", "payExchangeInfoHead", "payExchangeInfoLists", "serviceTime", "certNo", "signValue" })
public class HgCheckVO extends HgSignVO{
	private String certNo;
	private String signValue;

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

}
