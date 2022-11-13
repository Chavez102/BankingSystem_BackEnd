package com.Revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.Revature.model.Account;
import com.Revature.utils.Database;

@Repository
public class AccountDAOImpl implements AccountDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Account> findAccountbyUserID(int ID) {
		
		List<Account> accountList =executeFindAccountsStatement(jdbcTemplate, "SELECT * FROM ACCOUNTS WHERE account_user_id =?",Integer.toString(ID));
		
		
		return accountList;
	}
	
	@Override
	public Account findAccountbyAccountNumber(int accountNumber) {
		// TODO Auto-generated method stub
		List<Account> accountList =executeFindAccountsStatement(jdbcTemplate, "SELECT * FROM ACCOUNTS WHERE account_number = ?",Integer.toString(accountNumber));
		if (accountList.isEmpty()) 
			return null;
		
		
		return accountList.get(0);
		
	}
	
	
	@Override
	public boolean updateAccountBalance(int accountNumber, double newBalance) {
		return Database.executeStatement(jdbcTemplate, "UPDATE accounts SET account_balance = ? WHERE account_number = ?", 
																					Double.toString(newBalance),Integer.toString(accountNumber) );
		
	}
	 
  public List<Account> executeFindAccountsStatement(JdbcTemplate jdbcTemplate,String sql, String...args) { 
		 
		 List<Account> myList= new ArrayList<>();
		 
		 PreparedStatementCreator dealwithStatement = new PreparedStatementCreator() { 
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement stmt= con.prepareStatement(sql);
				int i=1;
      for (String arg:args) {  
          double d;
          try
          {
            d =Double.parseDouble(arg);
            stmt.setDouble(i, d ); 
          }
          catch(NumberFormatException e)
          {
            //arg not double
            stmt.setString(i, arg ); 
          } 
          i++;
        }  
       stmt.execute(); 
				return stmt;
			}   
		 };
		 
		 ResultSetExtractor<Account> dealwithresults=new ResultSetExtractor<Account>(){ 
				@Override
				public Account extractData(ResultSet rs) throws SQLException, DataAccessException {

				  while (rs.next()) {
				  	myList.add(
		                new Account(rs.getInt(1), //account number
		                		rs.getInt(2),			//account user id
		                		rs.getString(3), 			//acccount type
		                		rs.getDouble(4),			//account balance
		                		rs.getString(5) 			//account name 
		                )			
		         );
		      }
				  
					return null;
				}
			 } ;   
		 
		jdbcTemplate.query(dealwithStatement, dealwithresults);  
		  
		 return myList; 
	 }
 
}
