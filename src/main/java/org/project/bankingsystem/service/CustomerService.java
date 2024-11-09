package org.project.bankingsystem.service;

import org.project.bankingsystem.exception.CustomerException;
import org.project.bankingsystem.model.Customer;

import java.util.List;

public interface CustomerService {
    boolean addCustomer(Customer customer) throws CustomerException;
    boolean updateCustomer(Customer customer)throws CustomerException;
    boolean deleteCustomer(int customerId)throws CustomerException;
    Customer getCustomerById(int customerId)throws CustomerException;
    Customer getCustomerByUsername(String username)throws CustomerException;
    List<Customer> getAllCustomers() throws CustomerException;
    boolean validate(String username,String password) throws CustomerException;
}
