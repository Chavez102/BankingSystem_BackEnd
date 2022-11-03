package com.Revature.BankingSystem_BackEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@ComponentScan("com.*")
public class BankingSystemBackEndApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(BankingSystemBackEndApplication.class, args);
	}
	
	  
	   
	
	

}
