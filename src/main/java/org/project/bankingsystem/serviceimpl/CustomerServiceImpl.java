package org.project.bankingsystem.serviceimpl;

import org.project.bankingsystem.dao.CustomerDao;
import org.project.bankingsystem.daoImpl.CustomerDaoImpl;
import org.project.bankingsystem.exception.CustomerException;
import org.project.bankingsystem.model.Customer;
import org.project.bankingsystem.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    CustomerDao dao = new CustomerDaoImpl();
    @Override
    public boolean addCustomer(Customer customer) throws CustomerException {
        return dao.addCustomer(customer);
    }

    @Override
    public boolean updateCustomer(Customer customer) throws CustomerException {
        return dao.updateCustomer(customer);
    }

    @Override
    public boolean deleteCustomer(int customerId) throws CustomerException {
        return dao.deleteCustomer(customerId);
    }

    @Override
    public Customer getCustomerById(int customerId) throws CustomerException {
        return dao.getCustomerById(customerId);
    }

    @Override
    public Customer getCustomerByUsername(String username) throws CustomerException {
        return dao.getCustomerByUsername(username);
    }

    @Override
    public List<Customer> getAllCustomers() throws CustomerException {
        return dao.getAllCustomers();
    }

    @Override
    public boolean validate(String username, String password) throws CustomerException {
        return dao.validate(username, password);
    }
}
