package com.websocket.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String main(HttpServletRequest request) {
		return "index";
	}
	
	@GetMapping("/test")
	public String test(HttpServletRequest request) {
		return "cookie";
	}
}
