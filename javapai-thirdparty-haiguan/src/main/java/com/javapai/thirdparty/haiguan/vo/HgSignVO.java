package com.javapai.thirdparty.haiguan.vo;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = { "sessionID", "payExchangeInfoHead", "payExchangeInfoLists", "serviceTime" })
public class HgSignVO {
	private String sessionID;
	private Head179 payExchangeInfoHead;
	private List<Body179> payExchangeInfoLists;
	private String serviceTime;

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public Head179 getPayExchangeInfoHead() {
		return payExchangeInfoHead;
	}

	public void setPayExchangeInfoHead(Head179 payExchangeInfoHead) {
		this.payExchangeInfoHead = payExchangeInfoHead;
	}

	public List<Body179> getPayExchangeInfoLists() {
		return payExchangeInfoLists;
	}

	public void setPayExchangeInfoLists(List<Body179> payExchangeInfoLists) {
		this.payExchangeInfoLists = payExchangeInfoLists;
	}

	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

}
