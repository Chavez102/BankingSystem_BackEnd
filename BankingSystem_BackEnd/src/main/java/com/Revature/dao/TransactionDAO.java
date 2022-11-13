package com.Revature.dao;

import java.util.List;

import com.Revature.model.Transaction;

public interface TransactionDAO {
	public List<Transaction> geTransactions(int tx_account_number);
	
	boolean addTransaction(Transaction newTransaction);

}
