package p1;

import java.util.concurrent.atomic.AtomicLong;


abstract class Account {
private static final AtomicLong NEXT = new AtomicLong(1000000);
protected final String accountNumber;
protected final String ownerName;
protected double balance;


protected Account(String ownerName, double initialBalance) {
this.accountNumber = String.valueOf(NEXT.getAndIncrement());
this.ownerName = ownerName;
this.balance = initialBalance;
}


public String getAccountNumber() { return accountNumber; }
public String getOwnerName() { return ownerName; }


// All balance changing operations are synchronized on the account instance
public synchronized void deposit(double amount, TransactionLogger logger) throws InvalidAmountException {
if (amount <= 0) throw new InvalidAmountException("Deposit amount must be positive");
balance += amount;
if (logger != null) logger.log(new Transaction(Transaction.Type.DEPOSIT, accountNumber, accountNumber, amount));
}


public synchronized void withdraw(double amount, TransactionLogger logger) throws InvalidAmountException, InsufficientBalanceException {
if (amount <= 0) throw new InvalidAmountException("Withdraw amount must be positive");
if (!canWithdraw(amount)) throw new InsufficientBalanceException("Insufficient balance for withdrawal: " + amount);
balance -= amount;
if (logger != null) logger.log(new Transaction(Transaction.Type.WITHDRAW, accountNumber, accountNumber, amount));
}


protected abstract boolean canWithdraw(double amount);


public synchronized double getBalance() {
return balance;
}


@Override
public String toString() {
return String.format("Account[%s] owner=%s balance=%.2f", accountNumber, ownerName, balance);
}
}