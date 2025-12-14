package p1;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class Transaction {
enum Type { DEPOSIT, WITHDRAW, TRANSFER }


private final Type type;
private final String fromAccount;
private final String toAccount;
private final double amount;
private final LocalDateTime timestamp;


public Transaction(Type type, String fromAccount, String toAccount, double amount) {
this.type = type;
this.fromAccount = fromAccount;
this.toAccount = toAccount;
this.amount = amount;
this.timestamp = LocalDateTime.now();
}


@Override
public String toString() {
DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
return String.format("[%s] %s: from=%s to=%s amount=%.2f", timestamp.format(fmt), type, fromAccount, toAccount, amount);
}
}