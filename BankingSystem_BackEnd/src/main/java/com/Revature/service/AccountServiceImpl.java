package com.Revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Revature.dao.AccountDAO;
import com.Revature.dao.TransactionDAO;
import com.Revature.model.Account;
import com.Revature.model.Transaction;

@Component
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountDAO accountDAO;
	
	@Autowired
	TransactionDAO transactionDAO;

	@Override
	public List<Account> findAccountsbyUserID(int ID) {
		// TODO Auto-generated method stub
		return accountDAO.findAccountbyUserID(ID);
	}

	@Override
	public Account findAccountbyAccountNumber(int accountNumber) {
		// TODO Auto-generated method stub
		
		return accountDAO.findAccountbyAccountNumber(accountNumber);
	}

	@Override
	public boolean transerMoney(int sender_account_number, int receiver_account_number, double valueAmount,
																String tx_type,String tx_description) {
		// TODO Auto-generated method stub
		Account senderAccount = accountDAO.findAccountbyAccountNumber(sender_account_number);
		
		double senderBalance= senderAccount.getAccount_balance(); 
		
		//check if senderAccount has enough money to transfer
		if(senderBalance<valueAmount) {
			return false;									
		} 
		
		senderBalance   = senderBalance-valueAmount;  
		
		//create Sender transaction
		Transaction senderTransaction =new Transaction() ;
		senderTransaction.setTx_account_number(sender_account_number);
		senderTransaction.setTx_foreign_account_number(receiver_account_number);
		senderTransaction.setTx_value(valueAmount);
		senderTransaction.setTx_type(tx_type);
		senderTransaction.setTx_description(tx_description);
		
		//if anything anything fails return false;
		if ( !transactionDAO.addTransaction(senderTransaction) ||  
				!accountDAO.updateAccountBalance(sender_account_number, senderBalance) )
				return false;
		
		//
		
		Account receiverAccount= accountDAO.findAccountbyAccountNumber(receiver_account_number); 
		if(receiverAccount!=null) {
			double receiverBalance = receiverAccount.getAccount_balance();
			receiverBalance = receiverBalance+valueAmount;  
			if (! accountDAO.updateAccountBalance(receiver_account_number, receiverBalance) ) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean createAccount(Account account) {
		accountDAO.createAccount(account);
		return false;
	}

	

 
}
