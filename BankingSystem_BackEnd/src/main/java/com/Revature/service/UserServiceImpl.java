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


	@Override
	public User findUserbyUserName(String username) {
		
		// TODO Auto-generated method stub
		return userDAO.findUserbyUserName(username);
	}


	@Override
	public boolean updateEmail(int userId, String newEmail) {
		// TODO Auto-generated method stub
		return userDAO.updateEmail(userId, newEmail);
	}


	@Override
	public boolean updatePassword(int userId, String newPassword) {
		
		return userDAO.updatePassword(userId, newPassword);
	}

}
