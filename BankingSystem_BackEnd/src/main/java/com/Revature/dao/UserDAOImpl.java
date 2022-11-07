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

import com.Revature.model.User;
import com.Revature.utils.Database;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean register(User user) {
		  
		if ( isUser( user.getUser_name() ) ) {
			return false;
		} 

		return Database.executeStatement(jdbcTemplate,"INSERT INTO users"
				+ "(user_name,user_password,user_first_name,user_last_name,user_email) "
				+"VALUES (?,?,?,?,?)",
				user.getUser_name(),
				user.getUser_password(), 
				user.getUser_first_name(),
				user.getUser_last_name(),
				user.getUser_email()
				);
		
	}

	@Override
	public boolean logIn(String username, String password) {
		
		//check if username already exists
			if ( ! isUser( username) ) {
				return false;
			}
			
			//get list of users with the right user and right password
			List<User> userList = executeFindUsersStatement(jdbcTemplate,
					"SELECT * FROM users WHERE user_name=? AND user_password =?", 
					username , password);
			
			//return true if userList is not empty
			return ! userList.isEmpty(); 
	}
	
	public boolean isUser(String username) {
  	
  	List<User> userList = executeFindUsersStatement(jdbcTemplate,"SELECT * FROM users WHERE user_name=?", username);
  	System.out.println();
  	return ! userList.isEmpty(); 
  }
	
  public List<User> executeFindUsersStatement(JdbcTemplate jdbcTemplate,String sql, String...args) { 
		 
		 List<User> userList= new ArrayList<>();
		 
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
		 
		 ResultSetExtractor<User> dealwithresults=new ResultSetExtractor<User>(){ 
				@Override
				public User extractData(ResultSet rs) throws SQLException, DataAccessException {

				  while (rs.next()) {
		        userList.add(
		                new User(rs.getString(1), //user_
		                		rs.getString(2),			//user_name
		                		rs.getString(3), 			//user_password
		                		rs.getString(4),			//user_first_name
		                		rs.getString(5), 			//user_last_name
		                		rs.getString(6) 			//user_email
		                )			
		         );
		      }
				  
				  System.out.print(userList.toString());
					return null;
				}
			 } ;   
		 
		jdbcTemplate.query(dealwithStatement, dealwithresults);  
		  
		 return userList; 
	 }


}
