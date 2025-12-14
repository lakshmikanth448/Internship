package p1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


class TransactionLogger {
private final String filename;


public TransactionLogger(String filename) {
this.filename = filename;
}


public synchronized void log(Transaction tx) {
try (FileWriter fw = new FileWriter(filename, true);
PrintWriter pw = new PrintWriter(fw)) {
pw.println(tx.toString());
} catch (IOException e) {
System.err.println("Failed to write transaction log: " + e.getMessage());
}
}
}