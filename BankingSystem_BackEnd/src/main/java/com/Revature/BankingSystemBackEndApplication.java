package com.Revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication 
@ComponentScan("com.*") 
public class BankingSystemBackEndApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(BankingSystemBackEndApplication.class, args);
	}
	
	  
	   
	
	

}