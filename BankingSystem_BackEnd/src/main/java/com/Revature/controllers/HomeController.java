package com.Revature.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/home")
	@ResponseBody
	public String showHomePage() {
		
		System.out.println("-----------------------------------Show Home Page-------------------------------------------");
		
		HashMap<String,String> map = new HashMap<>();
		map.put("interesting", "very interesting");
		run ();
		return "hello world";
	}
	
	
	 public void run(String... args) {
	        String sql = "INSERT INTO users(user_name,user_password,user_first_name,user_last_name,user_email) "
	        		+ "VALUES ('FromAPI','pw123','Christina','lname','christina@revature.com');" ;
	        
	        
	        int rows = jdbcTemplate.update(sql);
	        if (rows > 0) {
	            System.out.println("A new row has been inserted.");
	        }
	    }
}
