package com.Revature.dao;

import org.springframework.stereotype.Service;

import com.Revature.model.User;

@Service
public interface UserDAO {
	
	
	public boolean register(User user);
	
	public boolean logIn(String username, String password);
	
	public User findUserbyUserName(String username);
	
	
	
	
}
