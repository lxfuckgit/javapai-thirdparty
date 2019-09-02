package com.javapai.thirdparty.haiguan.job;

import java.util.concurrent.Callable;

public class HaiguanRepayJob implements Callable<String> {
	String sessionID, orderNo;
	
	public HaiguanRepayJob(String orderNo, String sessionID) {
		// TODO Auto-generated constructor stub
		this.orderNo = orderNo;
		this.sessionID = sessionID;
	}

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
