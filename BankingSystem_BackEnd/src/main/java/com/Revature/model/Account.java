package com.Revature.model;

public class Account {

	int account_number  ;
	int account_user_id;  
	String account_type;  
	double account_balance;  
	String account_name ; 
	
	
	public Account() {
		super();
	}
	public Account(int account_number, int account_user_id, String account_type, double account_balance,
			String account_name) {
		super();
		this.account_number = account_number;
		this.account_user_id = account_user_id;
		this.account_type = account_type;
		this.account_balance = account_balance;
		this.account_name = account_name;
	}
	
	public Account( int account_user_id, String account_type, double account_balance,
			String account_name) {
		super(); 
		this.account_user_id = account_user_id;
		this.account_type = account_type;
		this.account_balance = account_balance;
		this.account_name = account_name;
	}
	
	
	
	public int getAccount_number() {
		return account_number;
	}
	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}
	public int getAccount_user_id() {
		return account_user_id;
	}
	public void setAccount_user_id(int account_user_id) {
		this.account_user_id = account_user_id;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public double getAccount_balance() {
		return account_balance;
	}
	public void setAccount_balance(double account_balance) {
		this.account_balance = account_balance;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	
	@Override
	public String toString() {
		return "\nAccount [account_number=" + account_number + ", account_user_id=" + account_user_id + ", account_type="
				+ account_type + ", account_balance=" + account_balance + ", account_name=" + account_name + "]";
	}
	
	
	
	
	
	
}
