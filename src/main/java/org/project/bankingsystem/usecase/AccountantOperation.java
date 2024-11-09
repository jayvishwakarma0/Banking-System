package org.project.bankingsystem.usecase;

import org.project.bankingsystem.model.Accountant;
import org.project.bankingsystem.service.AccountantService;
import org.project.bankingsystem.serviceimpl.AccountantServiceImpl;
import org.project.bankingsystem.usecase.functionalities.AccountSection;
import org.project.bankingsystem.usecase.functionalities.CustomerSection;
import org.project.bankingsystem.usecase.functionalities.LoanSection;

import java.util.Scanner;

public class AccountantOperation {
    static Scanner sc = new Scanner(System.in);

    public static void accountantOperation() {
        AccountantService accountantService = new AccountantServiceImpl();
        int option;

        do {
            System.out.println("1. Register\n2. Login\n0. Exit");
            option = sc.nextInt();
            switch (option) {
                case 1 -> {
                    System.out.print("Enter Name: ");
                    String name = sc.next();
                    System.out.print("Enter Username: ");
                    String username = sc.next();
                    System.out.print("Enter Password: ");
                    String password = sc.next();
                    Accountant accountant = new Accountant();
                    accountant.setName(name);
                    accountant.setUsername(username);
                    accountant.setPassword(password);
                    if (accountantService.register(accountant)) {
                        System.out.println("Now you can login to your account.");
                    }
                }
                case 2 -> {
                    System.out.print("Enter Username: ");
                    String username1 = sc.next();
                    System.out.print("Enter Password: ");
                    String password1 = sc.next();
                    if (accountantService.validate(username1, password1)) {
                        System.out.println("Welcome Back!");
                        int choice;
                        do {
                            System.out.println("1. Customer Section\n2. Account Section\n3. Loan Section\n0. Exit");
                            choice = sc.nextInt();
                            switch (choice) {
                                case 1 -> CustomerSection.customerInfo();
                                case 2 -> AccountSection.accountInfo();
                                case 3 -> LoanSection.loanInfo();
                                case 0 -> System.out.println("Exiting....");
                                default -> System.out.println("Invalid Choice! Please Try Again...");
                            }
                            System.out.println();
                        } while (choice != 0);
                    } else {
                        System.out.println("Username or password is incorrect.");
                    }
                }
                case 0 -> System.out.println("Exiting....");
                default -> System.out.println("Invalid Choice! Please Try Again...");
            }

        } while (option != 0);
    }
}
