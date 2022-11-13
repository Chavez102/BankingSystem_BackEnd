package com.Revature.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Transaction {
	int tx_id ;
	int tx_account_number ;
	int tx_foreign_account_number ;
	double tx_value ;
	String tx_type ;
	String tx_description;
	
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a z") 
	Timestamp tx_date ;
	
	
	
	public Transaction() {
		super();
	}


	public Transaction(int tx_id, int tx_account_number, int tx_foreign_account_number, double tx_value, String tx_type,
			Timestamp tx_date, String tx_description) {
		super();
		this.tx_id = tx_id;
		this.tx_account_number = tx_account_number;
		this.tx_foreign_account_number = tx_foreign_account_number;
		this.tx_value = tx_value;
		this.tx_type = tx_type;
		this.tx_date = tx_date;
		this.tx_description = tx_description;
	}


	public int getTx_id() {
		return tx_id;
	} 

	public void setTx_id(int tx_id) {
		this.tx_id = tx_id;
	} 

	public int getTx_account_number() {
		return tx_account_number;
	} 

	public void setTx_account_number(int tx_account_number) {
		this.tx_account_number = tx_account_number;
	} 

	public int getTx_foreign_account_number() {
		return tx_foreign_account_number;
	}


	public void setTx_foreign_account_number(int tx_foreign_account_number) {
		this.tx_foreign_account_number = tx_foreign_account_number;
	} 

	public double getTx_value() {
		return tx_value;
	} 

	public void setTx_value(double tx_value) {
		this.tx_value = tx_value;
	} 

	public String getTx_type() {
		return tx_type;
	}


	public void setTx_type(String tx_type) {
		this.tx_type = tx_type;
	}
 
	public Timestamp getTx_date() {
		return tx_date;
	}

	public void setTx_date(Timestamp tx_date) {
		this.tx_date = tx_date;
	}

	public String getTx_description() {
		return tx_description;
	}

	public void setTx_description(String tx_description) {
		this.tx_description = tx_description;
	}


	@Override
	public String toString() {
		return "\nTransaction [tx_id=" + tx_id + ", tx_account_number=" + tx_account_number + ", tx_foreign_account_number="
				+ tx_foreign_account_number + ", tx_value=" + tx_value + ", tx_type=" + tx_type + ", tx_date=" + tx_date
				+ ", tx_description=" + tx_description + "]";
	}

}
