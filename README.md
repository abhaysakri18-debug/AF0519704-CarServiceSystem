# 🚗 Car Service Record Management System

A console-based **Java JDBC** application to manage car service records — built with MySQL and minimal dependencies.

---

## 📋 Features

- ➕ Add new car service records
- 📄 View all service records
- 🔍 Search record by ID
- ✏️ Update existing records
- 🗑️ Delete records
- Console menu-driven interface with user input

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java (JDK 21) |
| Database | MySQL 8.x |
| Connectivity | JDBC (Java Database Connectivity) |
| IDE | Eclipse |
| Driver | MySQL Connector/J |

---

## 📁 Project Structure

```
CarServiceSystem/
├── src/
│   └── car/
│       ├── CarServiceRecord.java   # Model / POJO class
│       ├── CarServiceDAO.java      # All DB operations (CRUD)
│       └── Main.java               # Entry point + menu
├── lib/
│   └── mysql-connector-j-8.x.x.jar
└── module-info.java
```

---

## 🗄️ Database Setup

Run the following SQL in **MySQL Workbench** or terminal:

```sql
CREATE DATABASE car_service_db;
USE car_service_db;

CREATE TABLE car_service_records (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    owner_name    VARCHAR(100) NOT NULL,
    car_model     VARCHAR(100) NOT NULL,
    license_no    VARCHAR(50)  NOT NULL,
    service_date  DATE         NOT NULL,
    service_type  VARCHAR(100) NOT NULL,
    cost          DOUBLE       NOT NULL,
    mechanic      VARCHAR(100)
);
```

---

## ⚙️ Configuration

Open `CarServiceDAO.java` and update your credentials:

```java
static final String URL  = "jdbc:mysql://localhost:3306/car_service_db";
static final String USER = "root";
static final String PASS = "your_password";   // ← change this
```

---

## 🚀 How to Run

### In Eclipse
1. Clone or download this repository
2. Open Eclipse → **File → Import → Existing Project**
3. Right-click the project → **Build Path → Add External JARs**
4. Add `mysql-connector-j-8.x.x.jar` from the `lib/` folder
5. Open `module-info.java` and ensure it contains:
   ```java
   module car {
       requires java.sql;
   }
   ```
6. Right-click `Main.java` → **Run As → Java Application**

### From Terminal
```bash
# Compile
javac -cp ".;lib/mysql-connector-j-8.x.x.jar" src/car/*.java

# Run (Windows)
java -cp ".;lib/mysql-connector-j-8.x.x.jar;src" car.Main

# Run (Linux/Mac)
java -cp ".:lib/mysql-connector-j-8.x.x.jar:src" car.Main
```

---

## 📦 Packages Used

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.Scanner;
```

Minimum imports — no frameworks, no extra libraries.

---

## 💻 Sample Output

```
=== Car Service Record Management System ===

1. Add Record
2. View All Records
3. Search by ID
4. Update Record
5. Delete Record
0. Exit
Choice: 1

Owner Name        : Abhay
Car Model         : Honda City
License No        : KA01AB1234
Date (YYYY-MM-DD) : 2025-01-10
Service Type      : Oil Change
Cost              : 800
Mechanic          : Raju

Record added successfully!
```

---

## 📌 Prerequisites

- Java JDK 21+
- MySQL 8.x installed and running
- MySQL Connector/J JAR downloaded from [dev.mysql.com](https://dev.mysql.com/downloads/connector/j/)

---

## 👤 Author

**Abhay**  
Java | JDBC | MySQL  
Built as part of academic coursework — Car Service Record Management System

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).# AF0519704-CarServiceSystem
