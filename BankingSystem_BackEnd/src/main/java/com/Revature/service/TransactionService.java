package com.Revature.service;

import java.util.List;

import com.Revature.model.Transaction;

public interface TransactionService {
	
	public List<Transaction> geTransactions(int tx_account_number);
	
}
