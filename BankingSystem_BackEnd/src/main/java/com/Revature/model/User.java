package com.Revature.model;

public class User {
	String user_id;
	String user_name;
	String user_password;
	String user_first_name;
	String user_last_name;
	String user_email;
	public User(String user_id, String user_name, String user_password, String user_first_name, String user_last_name,
			String user_email) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_first_name = user_first_name;
		this.user_last_name = user_last_name;
		this.user_email = user_email;
	}
	
	public User(String user_name, String user_password) {
		super();
		this.user_name = user_name;
		this.user_password = user_password;
	}

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_first_name() {
		return user_first_name;
	}
	public void setUser_first_name(String user_first_name) {
		this.user_first_name = user_first_name;
	}
	public String getUser_last_name() {
		return user_last_name;
	}
	public void setUser_last_name(String user_last_name) {
		this.user_last_name = user_last_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	@Override
	public String toString() {
		return "\nUser [user_id=" + user_id + ", user_name=" + user_name + ", user_password=" + user_password
				+ ", user_first_name=" + user_first_name + ", user_last_name=" + user_last_name + ", user_email=" + user_email
				+ "]";
	}
	
	
	
}
