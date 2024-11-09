package org.project.bankingsystem.usecase;

import java.util.Scanner;

public class BankApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice ;
        do {
            System.out.println("***** Welcome to the Bank *****");
            System.out.println("1. Accountant\n2. Customer\n0. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> AccountantOperation.accountantOperation();
                case 2 -> CustomerOperation.customerOperation();
                case 0 -> System.out.println("Exiting....");
                default -> System.out.println("Invalid Choice! Please Try Again...");
            }
            System.out.println();
        } while (choice!=0);
    }
}
