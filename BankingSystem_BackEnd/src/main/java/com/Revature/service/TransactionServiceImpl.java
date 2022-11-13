package com.Revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Revature.dao.TransactionDAO;
import com.Revature.model.Transaction;

@Component
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	TransactionDAO transactionDAO;
	

	@Override
	public List<Transaction> geTransactions(int tx_account_number) {
		return transactionDAO.geTransactions(tx_account_number);
	}




}
