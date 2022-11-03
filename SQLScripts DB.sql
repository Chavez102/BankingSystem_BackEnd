create table if not exists users (
	user_id serial primary key unique,
	user_name VARCHAR(50) unique NOT NULL,
    user_password VARCHAR(50) NOT NULL,
    user_first_name VARCHAR(50) NOT NULL,
    user_last_name VARCHAR(50) NOT NULL,
    user_email VARCHAR(50) NOT NULL
);

create table if not exists accounts (
	account_number serial PRIMARY KEY UNIQUE,
	account_user_id INTEGER REFERENCES users(user_id),
	account_type VARCHAR(50) NOT NULL,
	account_balance FLOAT NOT NULL DEFAULT 0.00,
	account_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS transactions (
	tx_id serial PRIMARY KEY UNIQUE,
	tx_account_number INTEGER REFERENCES accounts(account_number),
	tx_foreign_account_number INTEGER DEFAULT null,
	tx_value FLOAT NOT NULL,
	tx_type VARCHAR(50) NOT NULL,
	tx_date TIMESTAMP DEFAULT current_timestamp,
	tx_description VARCHAR(100) NOT NULL
);


INSERT INTO users(user_name,user_password,user_first_name,user_last_name,user_email) VALUES
	('christinaTest','pw123','Christina','lname','christina@revature.com'),
	('bryanTest','pw123','Bryan','Chavez','bryanC@revature.net'),
	('nahiaTest','pw123','Nahia','Riviera','nRiviera@revature.net');

INSERT INTO accounts(account_user_id,account_type,account_balance,account_name) VALUES
	(1,'saving',150.25,'my savings'),
	(2,'checking',501.35,'home checking'),
	(2,'saving',22.00,'rainy day'),
	(3,'checking',75.00,'home'),
	(3,'saving',600.22,'college fund');

INSERT INTO transactions(tx_account_number, tx_foreign_account_number,tx_value,tx_type,tx_description) VALUES 
	(1,DEFAULT,30.55,'credit','cash deposit'),
	(2,3,200.00,'credit','transfer from savings'),
	(3,2,200.00,'debit','transfer to checking'),
	(4,DEFAULT,135.00,'debit','walmart'),
	(5,DEFAULT,100.00,'credit','cash deposit');

SELECT * FROM users;
SELECT * FROM accounts ;
SELECT * FROM transactions;









