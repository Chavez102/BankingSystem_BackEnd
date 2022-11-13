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
import org.springframework.web.bind.annotation.RequestParam;

import com.Revature.model.Transaction;
import com.Revature.service.TransactionService;
import com.Revature.utils.Database;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/MyAccount")
public class TransactionController {
	@Autowired
	TransactionService transactionService;
	
	@GetMapping(value = "/Transactions") 
	public ResponseEntity< List<Transaction> > getTransactions(@RequestParam String tx_account_number, HttpServletRequest request ){
		if (! Database.isUserAuthenticated(request.getCookies())) 
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

		List<Transaction> allTransactionsList=transactionService.geTransactions(Integer.parseInt(tx_account_number)); 
		
		return ResponseEntity.status(HttpStatus.OK).body(allTransactionsList);
	}
	
	
	
	
	

}
