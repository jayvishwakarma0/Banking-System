package org.project.bankingsystem.usecase.functionalities;

import org.project.bankingsystem.model.Account;
import org.project.bankingsystem.model.Customer;
import org.project.bankingsystem.service.AccountService;
import org.project.bankingsystem.serviceimpl.AccountServiceImpl;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class AccountSection {
    static Scanner sc = new Scanner(System.in);
    public static void accountInfo(){
        AccountService ac = new AccountServiceImpl();
        int choice;
        do{
            System.out.println("1. Create Account\n2. Update Account\n3. Delete Account\n4. Get Account by account number\n5. Get Account by Customer Id\n6. Get all Accounts\n0. Exit");
            choice  = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Customer Id: ");
                    int customerId = sc.nextInt();
                    System.out.print("Enter Balance for account: ");
                    Double balance = sc.nextDouble();
                    Customer customer = new Customer();
                    customer.setId(customerId);
                    Account account = new Account();
                    account.setCustomer(customer);
                    account.setBalance(balance);
                    if (ac.createAccount(account)) {
                        System.out.println("Account created successfully");
                    }
                }
                case 2 -> {
                    System.out.print("Enter Account Number: ");
                    int updateAccount = sc.nextInt();
                    System.out.print("Enter Balance for account: ");
                    Double updatedBalance = sc.nextDouble();

                    Account updatedAccount =  ac.getAccountByAccountNumber(updateAccount);
                    updatedAccount.setBalance(updatedBalance);
                    if (ac.updateAccount(updatedAccount)) {
                        System.out.println("Account updated successfully");
                    }
                }
                case 3 -> {
                    System.out.print("Enter account number to delete: ");
                    int deleteAccount = sc.nextInt();
                    if (ac.deleteAccount(deleteAccount)) {
                        System.out.println("Account deleted successfully");
                    }
                }
                case 4 -> {
                    System.out.print("Enter Account number: ");
                    int getAccount = sc.nextInt();
                    Account account1 = ac.getAccountByAccountNumber(getAccount);
                    DecimalFormat df = new DecimalFormat("#,##0.00");
                    System.out.println("+---------------+---------------+----------------+");
                    System.out.printf("| %-13s | %-13s | %-14s |%n", "Account Number", "Customer ID", "Balance");
                    System.out.println("+---------------+---------------+----------------+");
                    System.out.printf("| %-13d | %-13d | %-14s |%n",
                            account1.getAccountNumber(),
                            account1.getCustomer().getId(),
                            "Rs." + df.format(account1.getBalance()));
                    System.out.println("+---------------+---------------+----------------+");
                }
                case 5 -> {
                    System.out.print("Enter Customer Id: ");
                    int getCustomerId = sc.nextInt();
                    Account account2 = ac.getAccountByCustomerId(getCustomerId);
                    DecimalFormat df2 = new DecimalFormat("#,##0.00");
                    System.out.println("+---------------+---------------+----------------+");
                    System.out.printf("| %-13s | %-13s | %-14s |%n", "Account Number", "Customer ID", "Balance");
                    System.out.println("+---------------+---------------+----------------+");
                    System.out.printf("| %-13d | %-13d | %-14s |%n",
                            account2.getAccountNumber(),
                            account2.getCustomer().getId(),
                            "Rs." + df2.format(account2.getBalance()));
                    System.out.println("+---------------+---------------+----------------+");
                }
                case 6 -> {
                    List<Account> accountList = ac.getAllAccounts();
                    DecimalFormat df3 = new DecimalFormat("#,##0.00");
                    System.out.println("+---------------+---------------+----------------+");
                    System.out.printf("| %-13s | %-13s | %-14s |%n", "Account Number", "Customer ID", "Balance");
                    System.out.println("+---------------+---------------+----------------+");
                    for (Account acc : accountList) {
                        System.out.printf("| %-13d | %-13d | %-14s |%n",
                                acc.getAccountNumber(),
                                acc.getCustomer().getId(),
                                "Rs." + df3.format(acc.getBalance()));
                    }
                    System.out.println("+---------------+---------------+----------------+");
                }
                case 0 -> System.out.println("Exiting....");
                default -> System.out.println("Invalid Choice! Please Try Again...");
            }
            System.out.println();
        } while (choice!=0);
    }
}
