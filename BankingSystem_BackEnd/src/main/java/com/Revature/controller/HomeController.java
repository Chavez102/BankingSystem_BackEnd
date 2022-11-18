package com.Revature.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Revature.model.Account;
import com.Revature.model.User;
import com.Revature.service.UserService;
import com.Revature.utils.Database;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class HomeController {
	@Autowired
	UserService userService;
	

	@PostMapping(value = "/register", consumes = { MediaType.APPLICATION_JSON_VALUE }
// 	       , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} 
	)
	@ResponseBody
	public ResponseEntity<Object> register(@RequestBody User user) {
		// Check if there was an error registering user
		if (!userService.register(user)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} 
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}
	
	@PostMapping(value = "/logIn", consumes = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<Object> logIn(@RequestBody User user, HttpServletResponse response) {
		if (!userService.logIn(user.getUser_name(), user.getUser_password())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		 
		Cookie authCookie = new Cookie("authenticated", "true");
		int day = 60 * 60 * 24;				//seconds in a day
		authCookie.setMaxAge(day * 7);
		authCookie.setPath("/");

		
		Cookie userNameCookie = new Cookie("userName", user.getUser_name());
		userNameCookie.setMaxAge(day*7);
		userNameCookie.setPath("/");
		
		response.addCookie(authCookie);
		response.addCookie(userNameCookie);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PostMapping("/logOut") 
	public ResponseEntity<Object> logOut(HttpServletRequest request, HttpServletResponse response) {
		
		Cookie[] mycookies = request.getCookies();
		Cookie c = null;
		
		for(Cookie temp : mycookies) {
			
			if (temp.getName().equals("authenticated")) {
				temp.setValue("false");
				c = temp; 
			}
		}

		response.addCookie(c);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	

	@GetMapping("/user") 
	public ResponseEntity<User> getUser(HttpServletRequest request, HttpServletResponse response) {

		if(! Database.isUserAuthenticated(request.getCookies()))
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		
		String userName = Database.getUserNameFromCookies(request.getCookies()); 
		
		User myUser = userService.findUserbyUserName(userName);
		myUser.setUser_password("");
		
		return ResponseEntity.status(HttpStatus.OK).body(myUser);
	}
	
	@PutMapping(
			value = "/email", consumes = { MediaType.APPLICATION_JSON_VALUE}
			
			)
	public ResponseEntity<Object> updateEmail(HttpServletRequest request,@RequestBody String Jsonresponse){
		if(! Database.isUserAuthenticated(request.getCookies()))
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		
		//get newEmail
		JSONObject jsonbody= null;
		
		String newEmail="";
		
		try {
			jsonbody = new JSONObject(Jsonresponse);
			
			//Mandatory fields in JSON
			newEmail=jsonbody.getString("newEmail");
			
			 
		} catch (JSONException e) { 
			System.out.println("Mandatory Json fields are : sender_account_number, value, type");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} 
		//
		
		
		
		String userName = Database.getUserNameFromCookies(request.getCookies()); 
		User myUser = userService.findUserbyUserName(userName);
		
		if (!userService.updateEmail(Integer.parseInt( myUser.getUser_id() ), newEmail) ) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).build();
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
