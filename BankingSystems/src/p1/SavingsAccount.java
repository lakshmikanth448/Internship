package p1;

class SavingsAccount extends Account {
private final double minBalance;
private final double interestRate; // percent, not used in operations here but included for completeness


public SavingsAccount(String ownerName, double initialBalance, double minBalance, double interestRate) {
super(ownerName, initialBalance);
this.minBalance = minBalance;
this.interestRate = interestRate;
}


@Override
protected boolean canWithdraw(double amount) {
return balance - amount >= minBalance;
}
}
