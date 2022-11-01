package com.Revature.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	@ResponseBody
	public String showHomePage() {
		
		System.out.println("------------------------------------------------------------------------------");
		
		HashMap<String,String> map = new HashMap<>();
		map.put("interesting", "very interesting");
		return "hello world";
	}
}
