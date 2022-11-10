package com.Revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Revature.dao.AccountDAO;
import com.Revature.model.Account;
import com.Revature.model.User;

@Component
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountDAO accountDAO;

	@Override
	public List<Account> findAccountbyUserID(int ID) {
		// TODO Auto-generated method stub
		return accountDAO.findAccountbyUserID(ID);
	}

	

 
}
