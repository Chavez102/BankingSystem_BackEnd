# BankingSystem BackEnd

This Backend use Java SpringBoot along with Spring Web, Spring MVC to create endpoints and manage database connectivity with a postgreSQL database that we have developed. The sql statement used to create our database schema are present in the root directory of this Backend API.




## Backend EndPoints


### Endpoint: POST `http://localhost:8080/register`
#### Definition: Create a new User in database
#### Parameters: Json object
#### Example
    {
    "user_name":"5555fg",
    "user_password": "pass",
    "user_first_name": "first",
    "user_last_name": "last",
    "user_email": "email" 
    }
    
#
### Endpoint: POST `http://localhost:8080/logIn`
#### Definition: Logs In a user and adds cookies for the session
#### Parameters: Json object
#### Example
    {
    "user_name":"bryanTest",
    "user_password": "pw123"
    } 
    
#
### Endpoint: POST `http://localhost:8080/logOut`
#### Definition: Changes the User cookies unauthenticated to 'false'
#### Parameters: None
#

### Endpoint: PUT`http://localhost:8080/email`
#### Definition: Changes the email of a User
#### Parameters: Json object
#### Example
    {
    "newEmail": bryanchavez101@gmail.com"
    }
#
    
### Endpoint: GET `http://localhost:8080/Accounts`
#### Definition: Get all the Account associated with current Account
#### Parameters: None, endpoint uses the cookies created in LogIn endpoint to determine user 

#

### Endpoint: POST `http://localhost:8080/Accounts/Add`
#### Definition: Add new Account to User profile 
#### Parameters: Json object
#### Example
    {
	  "account_type" :"Savings" , 
	  "account_name": "Boat 3" 
    }

#

### Endpoint: GET `http://localhost:8080/MyAccount/Transactions?tx_account_number=2`
#### Definition: Get all transactions of an account 
#### Parameters: Key =`tx_account_number` value= [number]

#

### Endpoint: PUT `http://localhost:8080/Accounts/Transfer`
#### Definition: Add new Account to User profile 
#### Parameters: Json object
#### Example
    {
    "sender_account_number": 2,
    "receiver_account_number": 6,
    "value": 21,
    "description": "desription of transaction"
    }

 

## DataBase
DataBase system is composed of 3 main tables, users, accounts and transactions 

![Alt text](https://github.com/Chavez102/BankingSystem_BackEnd/blob/main/Database%20Representation.png?raw=true "DataBase title")




