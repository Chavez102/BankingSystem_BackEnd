package com.Revature.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.Revature.model.Account;
import com.Revature.model.User;
import com.Revature.service.AccountService;
import com.Revature.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class AccountController {
	@Autowired
	AccountService accountService;
	@Autowired
	UserService userService;
	
	@GetMapping("/Accounts") 
	public ResponseEntity< List<Account> > getAccounts(HttpServletRequest request, HttpServletResponse response) {
		String userName=null;
		Cookie[] mycookies = request.getCookies();
		Cookie c = null;
		
		for(Cookie temp : mycookies) {
			
			if (temp.getName().equals("userName")) {
				userName = temp.getValue();
				 
			}
		}
		
		User myUser = userService.findUserbyUserName(userName);
		
			
		String id = myUser.getUser_id();
		List<Account> list = accountService.findAccountbyUserID(Integer.parseInt(id));
		
		System.out.print(list.toString());
		
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	

}
