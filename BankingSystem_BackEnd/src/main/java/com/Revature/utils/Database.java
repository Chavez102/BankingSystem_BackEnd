package com.Revature.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.Revature.model.User;

@Repository
public class Database {
	
//	@Autowired
//	private JdbcTemplate myjdbcTemplate;
	
	
	 public static boolean executeStatement(JdbcTemplate jdbcTemplate,String sql, String...args) { 
		 
		 PreparedStatementCallback<Boolean> callbackFun = new PreparedStatementCallback<Boolean>() {
			 @Override  
			    public Boolean doInPreparedStatement(PreparedStatement stmt)  
			            throws SQLException, DataAccessException {  
			               
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
			        return stmt.execute();   
			    }   
		 };
		  try {
		 jdbcTemplate.execute(sql,callbackFun );
		  }
		  catch (Exception e){
		  	System.out.print("\nOOPsSomething went wrong in Database.executeStatement\n");
		  	return false;
		  }
		  
		  return true;
	 }
	 

	  
	  

		


}
