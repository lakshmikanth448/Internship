package p1;

class CurrentAccount extends Account {
private final double overdraftLimit;


public CurrentAccount(String ownerName, double initialBalance, double overdraftLimit) {
super(ownerName, initialBalance);
this.overdraftLimit = overdraftLimit;
}


@Override
protected boolean canWithdraw(double amount) {
return balance - amount >= -overdraftLimit;
}
}
