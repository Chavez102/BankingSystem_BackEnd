package com.Revature.service;

import org.springframework.stereotype.Service;

import com.Revature.model.User;


public interface UserService {
			public boolean register(User user);
			
			public boolean logIn(String username, String password);
			
			public User findUserbyUserName(String username);
}
