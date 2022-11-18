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

import com.Revature.model.Transaction;
import com.Revature.utils.Database;

@Repository
public class TransactionDAOImpl implements TransactionDAO{
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Transaction> geTransactions(int tx_account_number) {
		// TODO Auto-generated method stub
		List<Transaction> transactionsSentFromAccount = executeFindTransactionsStatement("SELECT * FROM transactions WHERE tx_account_number  =?",
				Integer.toString(tx_account_number) ) ;
		
		//returns list but list's tx_account_account_number = tx_foreign_account_number -> need to swap tx_account_number with tx_foreign_account_number
		List<Transaction> transactionsReceivedbyAccount = executeFindTransactionsStatement("SELECT * FROM transactions WHERE tx_foreign_account_number  =?",
				Integer.toString(tx_account_number) ) ;
		 
		//swap tx_account_number with tx_foreign_account_number
		//Add transactionsReceivedbyAccount to transactionsSentFromAccount
		for(Transaction tempTx: transactionsReceivedbyAccount) {
			int foreign = tempTx.getTx_account_number();
			int primary = tempTx.getTx_foreign_account_number();
			
			tempTx.setTx_foreign_account_number(foreign);
			tempTx.setTx_account_number(primary);
			if (tempTx.getTx_type().equals("debit")) {
				tempTx.setTx_type("credit");
			}
			
			transactionsSentFromAccount.add(tempTx);
		} 
		
		return transactionsSentFromAccount;
	}
	
	
	@Override
	public boolean addTransaction(Transaction newTransaction) {
		return Database.executeStatement(jdbcTemplate, "INSERT INTO transactions(tx_account_number, tx_foreign_account_number, tx_value,tx_type,tx_description) "
				+ "VALUES (?,?,?,?,?)",
				Integer.toString(newTransaction.getTx_account_number()), 
				Integer.toString(newTransaction.getTx_foreign_account_number()), 
				Double.toString(newTransaction.getTx_value()),
				newTransaction.getTx_type(),
				newTransaction.getTx_description() );
		
	}
	
	
  public List<Transaction> executeFindTransactionsStatement(String sql, String...args) { 
		 
		 List<Transaction> myList= new ArrayList<>();
		 
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
		 
		 ResultSetExtractor<Transaction> dealwithresults=new ResultSetExtractor<Transaction>(){ 
				@Override
				public Transaction extractData(ResultSet rs) throws SQLException, DataAccessException {

				  while (rs.next()) {
				  	myList.add(
		                new Transaction(  
		                		rs.getInt(1),         //int tx_id ;
		                		rs.getInt(2),         //int tx_account_number ;
		                		rs.getInt(3),         //int tx_foreign_account_number ;
		                		rs.getDouble(4),      //double tx_value ;
		                		rs.getString(5),      //String tx_type ;
		                		rs.getTimestamp(6),        //Date tx_date ;
		                		rs.getString(7)				//String tx_description;		
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
