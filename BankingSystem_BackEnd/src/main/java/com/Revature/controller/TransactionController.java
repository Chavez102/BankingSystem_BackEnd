package com.Revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Revature.model.Transaction;
import com.Revature.service.TransactionService;
import com.Revature.utils.Database;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/MyAccount")
public class TransactionController {
	@Autowired
	TransactionService transactionService;
	
	@GetMapping(value = "/Transactions", consumes = { MediaType.APPLICATION_JSON_VALUE}) 
	public ResponseEntity< List<Transaction> > getTransactions(@RequestBody String Jsonresponse, HttpServletRequest request ){
		if (! Database.isUserAuthenticated(request.getCookies())) 
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		
		JSONObject jsonbody= null;
		int tx_account_number= -1;
		
		try {
			jsonbody = new JSONObject(Jsonresponse);
			 tx_account_number=jsonbody.getInt("tx_account_number");
			 
		} catch (JSONException e) { 
			System.out.println("Something happened in getTransaction Method");
			e.printStackTrace();
		}
		
		List<Transaction> allTransactionsList=transactionService.geTransactions(tx_account_number); 
		
		return ResponseEntity.status(HttpStatus.OK).body(allTransactionsList);
	}
	
	
	
	
	

}
