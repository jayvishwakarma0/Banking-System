package org.project.bankingsystem.usecase.functionalities;

import org.project.bankingsystem.model.Loan;
import org.project.bankingsystem.service.LoanService;
import org.project.bankingsystem.serviceimpl.LoanServiceImpl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class LoanSection { static Scanner sc = new Scanner(System.in);
    public static void loanInfo(){
        LoanService ls = new LoanServiceImpl();
        int choice;
        do{
            System.out.println("1. Allocate Loan\n2. Update Loan\n3. Delete Loan\n4. Get Loan by loan Id\n5. Get all Loans\n0. Exit");
            choice  = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Customer Id: ");
                    int customerId = sc.nextInt();
                    System.out.print("Enter Account Number: ");
                    int accountNumber = sc.nextInt();
                    System.out.print("Enter Loan amount to allocate: ");
                    Double loanAmount = sc.nextDouble();
                    if (ls.allocateLoan(customerId,accountNumber, loanAmount)) {
                        System.out.println("Loan allocated to this account");
                    }
                }
                case 2 -> {
                    System.out.print("Enter Loan Id: ");
                    int loanId = sc.nextInt();
                    System.out.print("Enter updated amount: ");
                    Double updatedAmount = sc.nextDouble();
                    if (ls.updateLoan(loanId, updatedAmount)) {
                        System.out.println("Loan updated successfully");
                    }
                }
                case 3 -> {
                    System.out.print("Enter Loan Id to delete: ");
                    int deleteLoan = sc.nextInt();
                    if (ls.deleteLoan(deleteLoan)) {
                        System.out.println("Loan deleted successfully");
                    }
                }
                case 4 -> {
                    System.out.print("Enter Loan Id: ");
                    int getLoan = sc.nextInt();
                    Loan loan = ls.getLoanById(getLoan);
                    DecimalFormat currencyFormat = new DecimalFormat("#,##0.00");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    System.out.println("+---------+---------------+---------------+---------------+-------------+");
                    System.out.printf("| %-7s | %-13s | %-13s | %-13s | %-11s |%n",
                            "Loan ID", "Customer ID", "Account Number", "Amount", "Loan Date");
                    System.out.println("+---------+---------------+---------------+---------------+-------------+");
                    System.out.printf("| %-7d | %-13d | %-13d | %-13s | %-11s |%n",
                            loan.getLoanId(),
                            loan.getCustomer().getId(),
                            loan.getAccount().getAccountNumber(),
                            "Rs." + currencyFormat.format(loan.getAmount()),
                            dateFormat.format(loan.getLoanDate()));
                    System.out.println("+---------+---------------+---------------+---------------+-------------+");
                }
                case 5 -> {
                    List<Loan> loanList = ls.getAllLoans();
                    DecimalFormat currencyFormat1 = new DecimalFormat("#,##0.00");
                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                    System.out.println("+---------+---------------+---------------+---------------+-------------+");
                    System.out.printf("| %-7s | %-13s | %-13s | %-13s | %-11s |%n",
                            "Loan ID", "Customer ID", "Account Number", "Amount", "Loan Date");
                    System.out.println("+---------+---------------+---------------+---------------+-------------+");
                    for (Loan loan1 : loanList) {
                        System.out.printf("| %-7d | %-13d | %-13d | %-13s | %-11s |%n",
                                loan1.getLoanId(),
                                loan1.getCustomer().getId(),
                                loan1.getAccount().getAccountNumber(),
                                "Rs." + currencyFormat1.format(loan1.getAmount()),
                                dateFormat1.format(loan1.getLoanDate()));
                    }
                    System.out.println("+---------+---------------+---------------+---------------+-------------+");
                }
                case 0 -> System.out.println("Exiting....");
                default -> System.out.println("Invalid Choice! Please Try Again...");
            }
            System.out.println();
        } while (choice!=0);
    }

}
