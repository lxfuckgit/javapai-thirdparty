package com.javapai.thirdparty.haiguan.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.javapai.thirdparty.haiguan.job.HaiguanRepayJob;

@RestController
public class HaiguanQueryController {
	
	@Value(value = "${haiguan.data.targetUrl}")
	private String targetUrl;
	
	ExecutorService pool = Executors.newFixedThreadPool(3);

	@PostMapping("/haiguan/platDataOpen")
//	public Map<String, Object> platDataOpen(HttpServletRequest request) {
	public Map<String, Object> platDataOpen(@RequestParam(value="openReq") String openReq) {
		JSONObject object = JSONObject.parseObject(openReq);
		
		/* 处理请求（视情况根据公司环境job化或是mq处理） */
		pool.submit(new HaiguanRepayJob(targetUrl, object.getString("orderNo"), object.getString("sessionID")));
		
		/* 返回报文给海关 */
		Map<String, Object> result = new HashMap<>();
		result.put("code", "10000");
		result.put("message", "接收成功!");
		result.put("serviceTime", System.currentTimeMillis());
		return result;
	}

}
