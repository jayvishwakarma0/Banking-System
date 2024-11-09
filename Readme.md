# Banking System Project

This project is a Java-based Banking System that uses Hibernate and JPA for ORM (Object-Relational Mapping) to manage database interactions. It is designed to simulate a basic banking system with features such as account management, loan allocations, transactions, and customer management.


## Project Overview
The Banking System project allows users (customers and accountants) to interact with a simulated bank environment. Customers can have multiple accounts and loans, while accountants can manage accounts, process loans, and view transaction histories.

This project uses Hibernate for ORM to manage interactions with an SQL database. The system enforces constraints (like non-negative balances and required relationships) to ensure data integrity.

## Features
* Account Management: Create, update, and delete bank accounts for customers.
* Loan Management: Allocate loans to customers, view loan information, and manage loan details.
* Transaction Management: Record transactions (e.g., deposits, withdrawals) with time-stamping.
* Customer and Accountant Authentication: Validate user credentials for secure access to system functionalities.
* Error Handling: Custom exceptions for specific banking operations, improving error clarity.

## Technologies Used
* Java 
* Hibernate & JPA: For ORM and database management
* MySQL: As the database management system
* Maven: For project build and dependency management

## Database Schema
### Entities
* Customer: Stores customer information and references accounts and loans.
* Account: Holds account details such as balance and associated transactions.
* Loan: Contains information about customer loans, including amount and date.
* TransactionTab: Stores transaction history for each account.
* Accountant: Stores details of bank employees responsible for managing accounts.

### Entity Relationships
* Customer ↔ Account (One-to-Many)
* Customer ↔ Loan (One-to-Many)
* Account ↔ TransactionTab (One-to-Many)


