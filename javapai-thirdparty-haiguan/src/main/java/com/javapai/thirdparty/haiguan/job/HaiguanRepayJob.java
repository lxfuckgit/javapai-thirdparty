package com.javapai.thirdparty.haiguan.job;

import java.io.IOException;
import java.util.concurrent.Callable;

import com.alibaba.fastjson.JSON;
import com.javapai.thirdparty.haiguan.service.HaiguanRepayService;
import com.javapai.thirdparty.haiguan.vo.HgCheckVO;

public class HaiguanRepayJob implements Callable<String> {
	private String sessionID, orderNo;
	
	public HaiguanRepayJob(String orderNo, String sessionID) {
		// TODO Auto-generated constructor stub
		this.orderNo = orderNo;
		this.sessionID = sessionID;
	}

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		String content = get179Data();
		
		HaiguanRepayService haiguanRepayService = new HaiguanRepayService();
		haiguanRepayService.repDataApply(JSON.parseObject(content, HgCheckVO.class));
		
		return content;
	}
	
	public String get179Data() throws ClientProtocolException, IOException {
		return "";
	}

}
