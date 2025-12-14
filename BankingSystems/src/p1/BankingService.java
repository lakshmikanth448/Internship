package p1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


class BankingService {
private final Map<String, Account> accounts = new ConcurrentHashMap<>();
private final TransactionLogger logger;


public BankingService(TransactionLogger logger) {
this.logger = logger;
}


public Account createSavingsAccount(String ownerName, double initialBalance, double minBalance, double interestRate) {
SavingsAccount acc = new SavingsAccount(ownerName, initialBalance, minBalance, interestRate);
accounts.put(acc.getAccountNumber(), acc);
return acc;
}


public Account createCurrentAccount(String ownerName, double initialBalance, double overdraftLimit) {
CurrentAccount acc = new CurrentAccount(ownerName, initialBalance, overdraftLimit);
accounts.put(acc.getAccountNumber(), acc);
return acc;
}


public Account getAccount(String accNo) {
return accounts.get(accNo);
}


// deposit and withdraw delegate to account object
public void deposit(String accNo, double amount) throws InvalidAmountException {
Account acc = getAccount(accNo);
if (acc == null) throw new IllegalArgumentException("Account not found: " + accNo);
acc.deposit(amount, logger);
}


public void withdraw(String accNo, double amount) throws InvalidAmountException, InsufficientBalanceException {
Account acc = getAccount(accNo);
if (acc == null) throw new IllegalArgumentException("Account not found: " + accNo);
acc.withdraw(amount, logger);
}


// transfer with deadlock-free locking order: lock on accounts' identity in consistent order
public void transfer(String fromAccNo, String toAccNo, double amount) throws InvalidAmountException, InsufficientBalanceException {
if (fromAccNo.equals(toAccNo)) throw new IllegalArgumentException("Cannot transfer to same account");
Account from = getAccount(fromAccNo);
Account to = getAccount(toAccNo);
if (from == null || to == null) throw new IllegalArgumentException("Account not found");
if (amount <= 0) throw new InvalidAmountException("Transfer amount must be positive");


Account firstLock, secondLock;
//to avoid deadlocks
if (from.getAccountNumber().compareTo(to.getAccountNumber()) < 0) {
firstLock = from; secondLock = to;
} else {
firstLock = to; secondLock = from;
}


synchronized (firstLock) {
synchronized (secondLock) {
// perform withdraw then deposit
if (!from.canWithdraw(amount)) throw new InsufficientBalanceException("Insufficient funds for transfer: " + amount);
from.balance -= amount; // direct change because we are inside locks
to.balance += amount;
if (logger != null) logger.log(new Transaction(Transaction.Type.TRANSFER, from.getAccountNumber(), to.getAccountNumber(), amount));
}
}
}


public void printAllAccounts() {
System.out.println("--- Accounts ---");
for (Account a : accounts.values()) {
System.out.println(a);
}
}
}
