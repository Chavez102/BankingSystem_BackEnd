package com.Revature.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.Cookie;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
@Repository
public class Database {
	
//	@Autowired
//	private JdbcTemplate myjdbcTemplate;
	
	 
	public static boolean isUserAuthenticated(Cookie[] cookies) {
		for( Cookie tempCookie: cookies ) {
			if( tempCookie.getName().equals("authenticated") ) {
				String authenticated= tempCookie.getValue();
				return authenticated.equals("true");
			}
		}
		 
		return false;
	}
	
	public static String getUserNameFromCookies(Cookie[] cookies) {
		String userName=null;
		 
		for(Cookie tempCookie : cookies) {
			
			if (tempCookie.getName().equals("userName")) 
				userName = tempCookie.getValue(); 
			
		}
		 
		return userName;
	}
	
	
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
