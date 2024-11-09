package org.project.bankingsystem.service;

import org.project.bankingsystem.exception.AccountException;
import org.project.bankingsystem.model.Account;

import java.util.List;

public interface AccountService {
    boolean createAccount(Account account) throws AccountException;
    boolean updateAccount(Account account)throws AccountException;
    boolean deleteAccount(int accountNumber)throws AccountException;
    Account getAccountByAccountNumber(int accountNumber)throws AccountException;
    Account getAccountByCustomerId(int customerId)throws AccountException;
    List<Account> getAllAccounts() throws AccountException;
}
