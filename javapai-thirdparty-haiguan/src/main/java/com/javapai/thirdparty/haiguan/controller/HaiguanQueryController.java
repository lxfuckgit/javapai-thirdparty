package com.javapai.thirdparty.haiguan.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
public class HaiguanQueryController {

	@PostMapping("/haiguan/platDataOpen")
//	public Map<String, Object> platDataOpen(HttpServletRequest request) {
	public Map<String, Object> platDataOpen(@RequestParam(value="openReq") String openReq) {
		Map<String, Object> result = new HashMap<>();
		JSONObject object = JSONObject.parseObject(openReq);
		
		result.put("code", "10000");
		result.put("message", "接收成功!");
		result.put("serviceTime", System.currentTimeMillis());
		return result;
	}

}
