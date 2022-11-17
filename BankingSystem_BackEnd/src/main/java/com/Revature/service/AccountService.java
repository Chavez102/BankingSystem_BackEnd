package com.Revature.service;

import java.util.List;

import com.Revature.model.Account;

public interface AccountService {
	
	List<Account> findAccountsbyUserID(int ID);
	
	Account findAccountbyAccountNumber(int accountNumber);
	
	boolean transerMoney(int sender_account_number,int receiver_account_number, double valueAmount,String tx_type,String tx_description);

	boolean createAccount(Account account);
}
