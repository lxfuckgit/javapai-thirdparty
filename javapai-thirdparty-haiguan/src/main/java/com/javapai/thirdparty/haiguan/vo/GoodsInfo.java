package com.javapai.thirdparty.haiguan.vo;

import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = { "gname", "itemLink" })
public class GoodsInfo {
	private String gname;
	private String itemLink;

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getItemLink() {
		return itemLink;
	}

	public void setItemLink(String itemLink) {
		this.itemLink = itemLink;
	}

}
