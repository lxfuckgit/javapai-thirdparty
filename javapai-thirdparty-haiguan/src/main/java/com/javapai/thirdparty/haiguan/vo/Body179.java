package com.javapai.thirdparty.haiguan.vo;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = { "orderNo", "goodsInfo", "recpAccount", "recpCode", "recpName" })
public class Body179 {
	private String orderNo;
	private List<GoodsInfo> goodsInfo;
	private String recpAccount;
	private String recpCode;
	private String recpName;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public List<GoodsInfo> getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(List<GoodsInfo> goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public String getRecpAccount() {
		return recpAccount;
	}

	public void setRecpAccount(String recpAccount) {
		this.recpAccount = recpAccount;
	}

	public String getRecpCode() {
		return recpCode;
	}

	public void setRecpCode(String recpCode) {
		this.recpCode = recpCode;
	}

	public String getRecpName() {
		return recpName;
	}

	public void setRecpName(String recpName) {
		this.recpName = recpName;
	}

}
