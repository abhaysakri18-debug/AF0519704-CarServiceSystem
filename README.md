Car Buying & Selling System using Java JDBC and MySQL
Project Overview

The Car Buying & Selling System is a console-based Java application developed using JDBC and MySQL. The system allows users to register, log in, view available cars, purchase cars, and manage car inventory. It also provides role-based access for Admin and Buyer operations.

This project demonstrates:

Java programming
JDBC connectivity
MySQL database integration
CRUD operations
Transaction management
Input validation
Inventory/stock management
Technologies Used
Technology	Purpose
Java	Application Development
JDBC	Database Connectivity
MySQL	Database Management
Eclipse IDE	Development Environment
SQL Workbench	Database Operations
Features
Buyer Features
Buyer Registration
Email Validation
Duplicate Email Prevention
Password Validation
Login Authentication
View Available Cars
Search Car by Brand
Buy Car
View Purchase History
Admin Features
Add Car
Update Car Details
Delete Car
View All Cars
View Sold Cars
Manage Stock Inventory
Additional Enhancements
Validation Features
Email format validation using Regex
Duplicate email checking
Password minimum length validation
Stock Management Features
Maintain available stock quantity
Reduce stock after purchase
Automatic SOLD status update
Out-of-stock handling
Project Structure
com.car
│
├── Main.java
├── CarSystem.java
└── DBConnection.java
Database Name
car_db
Database Tables
users Table
Column	Type
user_id	INT (PK)
name	VARCHAR
email	VARCHAR
password	VARCHAR
role	VARCHAR
cars Table
Column	Type
car_id	INT (PK)
brand	VARCHAR
model	VARCHAR
year	INT
price	DOUBLE
stock	INT
status	VARCHAR
purchases Table
Column	Type
purchase_id	INT (PK)
user_id	INT (FK)
car_id	INT (FK)
ER Diagram Description
One user can purchase multiple cars
One car can appear in multiple purchase records
purchases table acts as a bridge between users and cars

Relationships:

users → purchases (One-to-Many)
cars → purchases (One-to-Many)
JDBC Concepts Used
DriverManager
Connection
Statement
PreparedStatement
ResultSet
Transactions
Exception Handling
SQL Operations Used
Operation	Usage
INSERT	Add users, cars, purchases
SELECT	View and search data
UPDATE	Update cars and stock
DELETE	Delete car records
JOIN	View user purchases
FOREIGN KEY	Maintain relationships
Workflow
Buyer Workflow
Register/Login
      ↓
View Available Cars
      ↓
Search Car
      ↓
Buy Car
      ↓
Stock Updated Automatically
Admin Workflow
Login as Admin
      ↓
Add / Update / Delete Cars
      ↓
Manage Inventory
      ↓
View Sold Cars
How to Run the Project
Step 1 – Create Database

Run SQL scripts in MySQL Workbench.

Step 2 – Configure JDBC

Update database username and password in:

DBConnection.java
Step 3 – Add MySQL JDBC Driver

Add MySQL Connector JAR file to Eclipse Build Path.

Step 4 – Run Application

Run:

Main.java
Future Enhancements
GUI using Java Swing or JavaFX
Online payment integration
Admin analytics dashboard
Car image upload
Mobile application version
Cloud database integration
Conclusion

The Car Buying & Selling System successfully demonstrates database-driven application development using Java JDBC and MySQL. The project provides secure login functionality, stock management, transaction handling, and role-based access control, making it a complete mini project for academic purposes.

Developed By
ABHAY SAKRI

THANK YOU


