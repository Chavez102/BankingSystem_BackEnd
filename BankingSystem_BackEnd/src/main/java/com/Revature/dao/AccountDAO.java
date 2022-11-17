package com.Revature.dao;

import java.util.List;

import com.Revature.model.Account;

public interface AccountDAO {
	
	List<Account> findAccountbyUserID(int ID);
	
	Account findAccountbyAccountNumber(int accountNumber);
	
	boolean updateAccountBalance(int accountNumber, double newBalance);
	
	boolean createAccount(Account account);
	
	
}
