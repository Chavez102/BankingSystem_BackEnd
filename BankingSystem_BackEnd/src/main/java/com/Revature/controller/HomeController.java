package com.Revature.controller;

import java.net.URI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Revature.model.User;
import com.Revature.service.UserService;
import com.Revature.utils.Database;
@CrossOrigin
@Controller
public class HomeController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	UserService userService;

	@PostMapping(value = "/register", consumes = { MediaType.APPLICATION_JSON_VALUE }
// 	       , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} 
	)
	@ResponseBody
	public ResponseEntity<String> register(@RequestBody User user) {
		// Check if there was an error registering user
		if (!userService.register(user)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something went wrong Registering User");
		}

		return ResponseEntity.status(HttpStatus.CREATED).body("User created");

	}
	
	@PostMapping(value = "/logIn", consumes = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<String> logIn(@RequestBody User user, HttpServletResponse response) {

		if (!userService.logIn(user.getUser_name(), user.getUser_password())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username or Password wrong");
		}

		Cookie mycookie = new Cookie("authenticated", "true");
		int day = 60 * 60 * 24;
		mycookie.setMaxAge(day * 7);
		mycookie.setHttpOnly(true);
		response.addCookie(mycookie);

		return ResponseEntity.status(HttpStatus.OK).body("LOG IN SUCCESSFUL");
	}
	
	@PostMapping("/logOut") 
	public ResponseEntity<String> logOut(HttpServletRequest request, HttpServletResponse response) {
		
		Cookie[] mycookies = request.getCookies();
		Cookie c = null;
		
		for(Cookie temp : mycookies) {
			
			if (temp.getName().equals("authenticated")) {
				temp.setValue("false");
				c = temp;
				System.out.println(temp.getValue());
			}
		}

		response.addCookie(c);
		return ResponseEntity.status(HttpStatus.OK).body("LOG OUT SUCCESSFUL");
	}
	
}
