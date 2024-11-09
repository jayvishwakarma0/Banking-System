package org.project.bankingsystem.usecase;

import org.project.bankingsystem.model.TransactionTab;
import org.project.bankingsystem.service.CustomerService;
import org.project.bankingsystem.service.TransactionService;
import org.project.bankingsystem.serviceimpl.CustomerServiceImpl;
import org.project.bankingsystem.serviceimpl.TransactionServiceImpl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class CustomerOperation {
    static Scanner sc = new Scanner(System.in);
    public static void customerOperation(){
        System.out.print("Enter Username: ");
        String username = sc.next();
        System.out.print("Enter Password: ");
        String password = sc.next();

        CustomerService cs = new CustomerServiceImpl();
        TransactionService ts = new TransactionServiceImpl();

        if(cs.validate(username,password)){
            System.out.println("Welcome Back!");
            int choice;
            do{
                System.out.println("1. Deposit Money\n2. Withdrawal Money\n3. Transfer Money\n4. Get Balance\n5. Get All Transactions\n0. Exit");
                choice  = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter Your account number: ");
                        int accountNumber1 = sc.nextInt();
                        System.out.print("Enter amount to be deposit: ");
                        Double depositAmount = sc.nextDouble();
                        if (ts.deposit(accountNumber1, depositAmount)) {
                            System.out.println("Amount deposit in your account successfully");
                        }
                    }
                    case 2 -> {
                        System.out.print("Enter Your account number: ");
                        int accountNumber2 = sc.nextInt();
                        System.out.print("Enter amount to be withdraw: ");
                        Double withdrawAmount = sc.nextDouble();
                        if (ts.withdrawal(accountNumber2, withdrawAmount)) {
                            System.out.println("Amount withdrawal from your account successfully");
                        }
                    }
                    case 3 -> {
                        System.out.print("Enter Your account number: ");
                        int fromAccountNumber = sc.nextInt();
                        System.out.print("Enter account number to transfer: ");
                        int toAccountNumber = sc.nextInt();
                        System.out.print("Enter amount to be transfer: ");
                        Double transferAmount = sc.nextDouble();
                        if (ts.transfer(fromAccountNumber, toAccountNumber, transferAmount)) {
                            System.out.println("Amount transfer from your account successfully");
                        }
                    }
                    case 4 -> {
                        System.out.print("Enter Your account number: ");
                        int accountNumber = sc.nextInt();
                        System.out.println("Your Account balance is: " + ts.getBalance(accountNumber));
                    }
                    case 5 -> {
                        List<TransactionTab> list = ts.getAllTransaction();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        System.out.println("+---------------+---------------+---------------+---------------+----------------------+");
                        System.out.printf("| %-13s | %-13s | %-13s | %-13s | %-18s |%n",
                                "Transaction ID", "Account ID", "Amount", "Type", "Transaction Date");
                        System.out.println("+---------------+---------------+---------------+---------------+----------------------+");
                        for (TransactionTab transaction : list) {
                            System.out.printf("| %-13d | %-13d | %-13.2f | %-13s | %-18s |%n",
                                    transaction.getTransactionId(),
                                    transaction.getAccount().getAccountNumber(),
                                    transaction.getAmount(),
                                    transaction.getTransactionType(),
                                    dateFormat.format(transaction.getTransactionDate()));
                        }
                        System.out.println("+---------------+---------------+---------------+---------------+----------------------+");
                    }
                    case 0 -> System.out.println("Exiting....");
                    default -> System.out.println("Invalid Choice! Please Try Again...");
                }
                System.out.println();
            } while (choice!=0);
        } else{
            System.out.println("Username or password is incorrect.");
        }
    }
}
