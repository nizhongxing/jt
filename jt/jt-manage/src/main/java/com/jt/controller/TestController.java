package com.jt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping("/getMsg")
	public String getMsg() {
		return "这是测试端口8093";
	}
}
