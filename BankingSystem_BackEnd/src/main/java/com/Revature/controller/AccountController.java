package com.Revature.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Revature.model.Account;
import com.Revature.model.User;
import com.Revature.service.AccountService;
import com.Revature.service.UserService;
import com.Revature.utils.Database;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Accounts")
@Controller
public class AccountController {
	@Autowired
	AccountService accountService;
	@Autowired
	UserService userService;
	
	@GetMapping("") // "works with http://localhost:8080/Accounts"
	public ResponseEntity< List<Account> > getAccounts(HttpServletRequest request, HttpServletResponse response) {

		if(! Database.isUserAuthenticated(request.getCookies()))
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		
		String userName = Database.getUserNameFromCookies(request.getCookies()); 
		
		User myUser = userService.findUserbyUserName(userName);
		
			
		String id = myUser.getUser_id();
		List<Account> list = accountService.findAccountsbyUserID(Integer.parseInt(id));
		
		
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@PutMapping(value = "/Transfer", consumes = { MediaType.APPLICATION_JSON_VALUE}) 
	public ResponseEntity<Object> transfer(@RequestBody String Jsonresponse, HttpServletRequest request ){
		if (! Database.isUserAuthenticated(request.getCookies())) 
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		
		JSONObject jsonbody= null;
		int sender_account_number= -1;
		int receiver_account_number= -1;
		double valueAmount=-1;
		
		String tx_type="";
		String tx_description="";
		
		try {
			jsonbody = new JSONObject(Jsonresponse);
			
			//Mandatory fields in JSON
			sender_account_number=jsonbody.getInt("sender_account_number");
			valueAmount=jsonbody.getDouble("value");
			tx_type = jsonbody.getString("type");
			
			//Optional fields in JSON
			if( jsonbody.has("receiver_account_number") )  receiver_account_number=jsonbody.getInt("receiver_account_number");  
			if( jsonbody.has("description") ) tx_description = jsonbody.getString("description");
			 
		} catch (JSONException e) { 
			System.out.println("Mandatory Json fields are : sender_account_number, value, type");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} 
		boolean transferSuccessful=accountService.transerMoney(sender_account_number,receiver_account_number, valueAmount, tx_type, tx_description );
		
		if ( ! transferSuccessful) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		 
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	

}
