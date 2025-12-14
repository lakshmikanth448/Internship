package p1;

import java.util.ArrayList;
import java.util.List;

public class BankingApp {
    public static void main(String[] args) throws InterruptedException {
        TransactionLogger logger = new TransactionLogger("transactions.log");
        BankingService service = new BankingService(logger);

        // Create accounts
        Account a1 = service.createSavingsAccount("Alice", 5000.0, 500.0, 3.5);
        Account a2 = service.createCurrentAccount("Bob", 2000.0, 1000.0);
        Account a3 = service.createSavingsAccount("Charlie", 10000.0, 1000.0, 4.0);

        System.out.println("Created accounts:");
        service.printAllAccounts();
        //=============================Menu driven=================================
        // Scanner sc = new Scanner(System.in);
        // boolean running = true;

        // while (running) {
        //     System.out.println("\n--- Banking Menu ---");
        //     System.out.println("1. Deposit");
        //     System.out.println("2. Withdraw");
        //     System.out.println("3. Transfer");
        //     System.out.println("4. Exit");
        //     System.out.print("Enter choice: ");

        //     int choice = sc.nextInt();

        //     try {
        //         switch (choice) {
        //             case 1:
        //                 System.out.print("Enter Account Number: ");
        //                 String depAcc = sc.next();
        //                 System.out.print("Enter Amount: ");
        //                 double depAmt = sc.nextDouble();
        //                 service.deposit(depAcc, depAmt);
        //                 System.out.println("Deposit successful");
        //                 break;

        //             case 2:
        //                 System.out.print("Enter Account Number: ");
        //                 String witAcc = sc.next();
        //                 System.out.print("Enter Amount: ");
        //                 double witAmt = sc.nextDouble();
        //                 service.withdraw(witAcc, witAmt);
        //                 System.out.println("Withdrawal successful");
        //                 break;

        //             case 3:
        //                 System.out.print("From Account Number: ");
        //                 String fromAcc = sc.next();
        //                 System.out.print("To Account Number: ");
        //                 String toAcc = sc.next();
        //                 System.out.print("Amount: ");
        //                 double amt = sc.nextDouble();
        //                 service.transfer(fromAcc, toAcc, amt);
        //                 System.out.println("Transfer successful");
        //                 break;

        //             case 4:
        //                 running = false;
        //                 break;

        //             default:
        //                 System.out.println("Invalid choice");
        //         }
        //     } catch (Exception e) {
        //         System.err.println("Operation failed: " + e.getMessage());
        //     }
        // }
//==============================================================================================================================================
        // Create worker threads to simulate concurrent users
        List<Thread> threads = new ArrayList<>();

        Runnable client1 = () -> {
            try {
                service.deposit(a1.getAccountNumber(), 1000);
                service.withdraw(a1.getAccountNumber(), 200);
                service.transfer(a1.getAccountNumber(), a2.getAccountNumber(), 300);
            } catch (Exception e) {
                System.err.println("Client1 error: " + e.getMessage());
            }
        };

        Runnable client2 = () -> {
            try {
                service.withdraw(a2.getAccountNumber(), 500);
                service.transfer(a2.getAccountNumber(), a3.getAccountNumber(), 700);
                service.deposit(a2.getAccountNumber(), 150);
            } catch (Exception e) {
                System.err.println("Client2 error: " + e.getMessage());
            }
        };

        Runnable client3 = () -> {
            try {
                service.transfer(a3.getAccountNumber(), a1.getAccountNumber(), 1200);
                service.withdraw(a3.getAccountNumber(), 50);
            } catch (Exception e) {
                System.err.println("Client3 error: " + e.getMessage());
            }
        };

        threads.add(new Thread(client1, "Client-1"));
        threads.add(new Thread(client2, "Client-2"));
        threads.add(new Thread(client3, "Client-3"));

        // Start threads
        for (Thread t : threads) t.start();
        // Wait for completion
        for (Thread t : threads) t.join();

        System.out.println("\nFinal account states:");
        service.printAllAccounts();

        System.out.println("\nTransaction log written to transactions.log in src directory.");
        System.out.println("Sample tail of the log file (last lines):");
        
        try (java.io.RandomAccessFile raf = new java.io.RandomAccessFile("transactions.log", "r")) {
            long len = raf.length();
            long start = Math.max(0, len - 1024);
            raf.seek(start);
            String s;
            while ((s = raf.readLine()) != null) {
                System.out.println(new String(s.getBytes("ISO-8859-1"), "UTF-8"));
            }
        } catch (Exception e) {
            System.out.println("Unable to show log tail: " + e.getMessage());
        }
    }
}

