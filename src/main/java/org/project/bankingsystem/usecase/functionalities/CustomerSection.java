package org.project.bankingsystem.usecase.functionalities;

import org.project.bankingsystem.model.Customer;
import org.project.bankingsystem.service.CustomerService;
import org.project.bankingsystem.serviceimpl.CustomerServiceImpl;

import java.util.List;
import java.util.Scanner;

public class CustomerSection {
    static Scanner sc = new Scanner(System.in);

    public static void customerInfo() {
        CustomerService cs = new CustomerServiceImpl();
        int choice;
        do {
            System.out.println("1. Register Customer\n2. Update Customer\n3. Delete Customer\n4. Get Customer by Customer Id\n5. Get Customer by Username\n6. Get all Customers\n0. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Customer Name: ");
                    String name = sc.next();
                    System.out.print("Enter Customer Username: ");
                    String username = sc.next();
                    System.out.print("Enter Customer Email: ");
                    String email = sc.next();
                    System.out.print("Enter Customer Password: ");
                    String password = sc.next();
                    Customer customer = new Customer();
                    customer.setName(name);
                    customer.setUsername(username);
                    customer.setEmail(email);
                    customer.setPassword(password);
                    if (cs.addCustomer(customer)) {
                        System.out.println("Customer added successfully");
                    }
                }
                case 2 -> {
                    System.out.print("Enter Customer Id to be updated: ");
                    int cid = sc.nextInt();
                    System.out.print("Enter Customer Name: ");
                    String updatedName = sc.next();
                    System.out.print("Enter Customer Username: ");
                    String updatedUsername = sc.next();
                    System.out.print("Enter Customer Email: ");
                    String updatedEmail = sc.next();
                    System.out.print("Enter Customer Password: ");
                    String updatedPassword = sc.next();
                    Customer updatedCustomer = cs.getCustomerById(cid);
                    updatedCustomer.setName(updatedName);
                    updatedCustomer.setUsername(updatedUsername);
                    updatedCustomer.setEmail(updatedEmail);
                    updatedCustomer.setPassword(updatedPassword);
                    if (cs.updateCustomer(updatedCustomer)) {
                        System.out.println("Customer updated successfully");
                    }
                }
                case 3 -> {
                    System.out.print("Enter Customer Id to delete: ");
                    int deleteCustomer = sc.nextInt();
                    if (cs.deleteCustomer(deleteCustomer)) {
                        System.out.println("Customer deleted successfully");
                    }
                }
                case 4 -> {
                    System.out.print("Enter Customer Id: ");
                    int getCustomerById = sc.nextInt();
                    Customer getCustomer = cs.getCustomerById(getCustomerById);
                    System.out.println("+---------------+-------------------------+----------------------+---------------------------+");
                    System.out.printf("| %-13s | %-23s | %-20s | %-25s |%n",
                            "Customer ID", "Username", "Name", "Email");
                    System.out.println("+---------------+-------------------------+----------------------+---------------------------+");
                    System.out.printf("| %-13d | %-23s | %-20s | %-25s |%n",
                            getCustomer.getId(),
                            getCustomer.getUsername(),
                            getCustomer.getName(),
                            getCustomer.getEmail());
                    System.out.println("+---------------+-------------------------+----------------------+---------------------------+");
                }
                case 5 -> {
                    System.out.print("Enter username: ");
                    String user = sc.next();
                    Customer customer2 = cs.getCustomerByUsername(user);
                    System.out.println("+---------------+-------------------------+----------------------+---------------------------+");
                    System.out.printf("| %-13s | %-23s | %-20s | %-25s |%n",
                            "Customer ID", "Username", "Name", "Email");
                    System.out.println("+---------------+-------------------------+----------------------+---------------------------+");
                    System.out.printf("| %-13d | %-23s | %-20s | %-25s |%n",
                            customer2.getId(),
                            customer2.getUsername(),
                            customer2.getName(),
                            customer2.getEmail());
                    System.out.println("+---------------+-------------------------+----------------------+---------------------------+");
                }
                case 6 -> {
                    List<Customer> customerList = cs.getAllCustomers();
                    System.out.println("+---------------+-------------------------+----------------------+---------------------------+");
                    System.out.printf("| %-13s | %-23s | %-20s | %-25s |%n",
                            "Customer ID", "Username", "Name", "Email");
                    System.out.println("+---------------+-------------------------+----------------------+---------------------------+");
                    for (Customer cstmr : customerList) {
                        System.out.printf("| %-13d | %-23s | %-20s | %-25s |%n",
                                cstmr.getId(),
                                cstmr.getUsername(),
                                cstmr.getName(),
                                cstmr.getEmail());
                    }
                    System.out.println("+---------------+-------------------------+----------------------+---------------------------+");
                }
                case 0 -> System.out.println("Exiting....");
                default -> System.out.println("Invalid Choice! Please Try Again...");
            }
            System.out.println();
        } while (choice != 0);
    }
}
