package com.Revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Revature.dao.UserDAO;
import com.Revature.model.User;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDAO userDAO;


	@Override
	public boolean register(User user) { 
		return userDAO.register(user);
		
	}


	@Override
	public boolean logIn(String username, String password) {
		
		return userDAO.logIn(username, password);
	}

}
