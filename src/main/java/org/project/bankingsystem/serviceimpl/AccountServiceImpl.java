package org.project.bankingsystem.serviceimpl;

import org.project.bankingsystem.dao.AccountDao;
import org.project.bankingsystem.daoImpl.AccountDaoImpl;
import org.project.bankingsystem.exception.AccountException;
import org.project.bankingsystem.model.Account;
import org.project.bankingsystem.service.AccountService;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    AccountDao dao = new AccountDaoImpl();
    @Override
    public boolean createAccount(Account account) throws AccountException {
        return dao.createAccount(account);
    }

    @Override
    public boolean updateAccount(Account account) throws AccountException {
        return dao.updateAccount(account);
    }

    @Override
    public boolean deleteAccount(int accountNumber) throws AccountException {
        return dao.deleteAccount(accountNumber);
    }

    @Override
    public Account getAccountByAccountNumber(int accountNumber) throws AccountException {
        return dao.getAccountByAccountNumber(accountNumber);
    }

    @Override
    public Account getAccountByCustomerId(int customerId) throws AccountException {
        return dao.getAccountByCustomerId(customerId);
    }

    @Override
    public List<Account> getAllAccounts() throws AccountException {
        return dao.getAllAccounts();
    }
}
