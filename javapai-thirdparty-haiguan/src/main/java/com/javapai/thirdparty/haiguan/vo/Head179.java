package com.javapai.thirdparty.haiguan.vo;

import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = { "guid", "initalRequest", "initalResponse", "ebpCode", "payCode", "payTransactionId", "totalAmount", "currency", "verDept", "payType", "tradingTime", "note" })
public class Head179 {
	private String guid;
	private String initalRequest;
	private String initalResponse;
	private String ebpCode;
	private String payCode;
	private String payTransactionId;
	private Long totalAmount;
	private String currency;
	private String verDept;
	private String payType;
	private String tradingTime;
	private String note;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getInitalRequest() {
		return initalRequest;
	}

	public void setInitalRequest(String initalRequest) {
		this.initalRequest = initalRequest;
	}

	public String getInitalResponse() {
		return initalResponse;
	}

	public void setInitalResponse(String initalResponse) {
		this.initalResponse = initalResponse;
	}

	public String getEbpCode() {
		return ebpCode;
	}

	public void setEbpCode(String ebpCode) {
		this.ebpCode = ebpCode;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public String getPayTransactionId() {
		return payTransactionId;
	}

	public void setPayTransactionId(String payTransactionId) {
		this.payTransactionId = payTransactionId;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getVerDept() {
		return verDept;
	}

	public void setVerDept(String verDept) {
		this.verDept = verDept;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getTradingTime() {
		return tradingTime;
	}

	public void setTradingTime(String tradingTime) {
		this.tradingTime = tradingTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
