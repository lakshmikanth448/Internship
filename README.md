Banking System

This project is a console-based Banking System developed using pure Core Java. It demonstrates key Java concepts such as Object-Oriented Programming (OOP), Exception Handling, Multithreading, and File-based Logging. The system simulates real-world banking operations where multiple users can perform transactions concurrently while ensuring data consistency and safety.
The application is designed for academic projects, interviews, and practical understanding of Core Java concepts without using any external frameworks.
________________________________________
Features
•	Account creation (Savings Account and Current Account)
•	Deposit, Withdraw, and Transfer operations
•	Custom exception handling for invalid inputs and insufficient balance
•	Multithreading to simulate multiple banking clients
•	Deadlock-free money transfer implementation
•	File-based transaction logging using Java I/O
•	Thread-safe operations using synchronization and concurrent collections
________________________________________
Technologies Used
•	Java SE (8 / 11 / 17 compatible)
•	Core Java concepts:
o	OOP (Inheritance, Polymorphism, Encapsulation, Abstraction)
o	Collections (ConcurrentHashMap)
o	Exception Handling (Custom Exceptions)
o	Multithreading & Synchronization
o	Java File I/O (FileWriter, BufferedWriter, RandomAccessFile)
________________________________________
Project Structure
BankingSystem/
│
├── src/
│   ├── p1/
│   │   ├── Account.java
│   │   ├── SavingsAccount.java
│   │   ├── CurrentAccount.java
│   │   ├── BankingService.java
│   │   ├── Transaction.java
│   │   ├── TransactionLogger.java
│   │   ├── InvalidAmountException.java
│   │   ├── InsufficientBalanceException.java
│   │   └── BankingApp.java
│
├── transactions.log   (generated at runtime)

________________________________________
How the System Works
1.	The application starts from BankingApp.java
2.	BankingService creates and manages bank accounts
3.	Multiple threads simulate different users performing transactions
4.	Synchronization ensures thread-safe operations
5.	All transactions are logged into transactions.log
6.	Final account balances and recent log entries are displayed
________________________________________
Steps to Run the Project (Eclipse IDE)
Step 1: Create Project
1.	Open Eclipse IDE
2.	Go to File → New → Java Project
3.	Project Name: BankingSystem
4.	Click Finish
Step 2: Create Package and Classes
1.	Right-click on src → New → Package
2.	Package name: p1
3.	Create all .java files inside this package
Step 3: Compile and Run
1.	Right-click on BankingApp.java
2.	Select Run As → Java Application
________________________________________
Steps to Run Using Command Line
Compile
cd BankingSystem/src
javac p1/*.java
Run
java p1.BankingApp
________________________________________
Transaction Log File
•	File Name: transactions.log
•	Location: Project root or src directory
•	Contents:
o	Deposit transactions
o	Withdraw transactions
o	Transfer transactions
•	Log file grows automatically as transactions occur
________________________________________
Sample Output
•	Initial account details
•	Concurrent transaction execution
•	Final account balances
•	Recent transaction logs printed to console
