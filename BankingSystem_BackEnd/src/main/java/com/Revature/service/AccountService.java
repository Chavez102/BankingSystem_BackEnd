package com.Revature.service;

import java.util.List;

import com.Revature.model.Account;
import com.Revature.model.User;

public interface AccountService {
	List<Account> findAccountbyUserID(int ID);

}
