package org.project.bankingsystem.dao;

import org.project.bankingsystem.exception.TransactionException;
import org.project.bankingsystem.model.TransactionTab;

import java.util.List;

public interface TransactionDao {
    boolean deposit(int accountNumber,Double amount) throws TransactionException;
    boolean withdrawal(int accountNumber,Double amount) throws TransactionException;
    boolean transfer(int fromAccountNumber, int toAccountNumber,Double amount) throws TransactionException;
    TransactionTab getTransactionsById(int transactionId) throws TransactionException;
    TransactionTab getTransactionByAccountId(int accountNumber) throws TransactionException;
    Double getBalance(int accountNumber) throws TransactionException;
    List<TransactionTab> getAllTransaction() throws TransactionException;
}
