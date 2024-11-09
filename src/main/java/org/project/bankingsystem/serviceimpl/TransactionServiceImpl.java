package org.project.bankingsystem.serviceimpl;

import org.project.bankingsystem.dao.TransactionDao;
import org.project.bankingsystem.daoImpl.TransactionDaoImpl;
import org.project.bankingsystem.exception.TransactionException;
import org.project.bankingsystem.model.TransactionTab;
import org.project.bankingsystem.service.TransactionService;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    TransactionDao dao = new TransactionDaoImpl();
    @Override
    public boolean deposit(int accountNumber,Double amount) throws TransactionException {
        return dao.deposit(accountNumber, amount);
    }

    @Override
    public boolean withdrawal(int accountNumber,Double amount) throws TransactionException {
        return dao.withdrawal(accountNumber, amount);
    }

    @Override
    public boolean transfer(int fromAccountNumber, int toAccountNumber, Double amount) throws TransactionException {
        return dao.transfer(fromAccountNumber, toAccountNumber, amount);
    }

    @Override
    public TransactionTab getTransactionsById(int transactionId) throws TransactionException {
        return dao.getTransactionsById(transactionId);
    }

    @Override
    public TransactionTab getTransactionByAccountId(int accountNumber) throws TransactionException {
        return dao.getTransactionByAccountId(accountNumber);
    }

    @Override
    public Double getBalance(int accountNumber) throws TransactionException {
        return dao.getBalance(accountNumber);
    }

    @Override
    public List<TransactionTab> getAllTransaction() throws TransactionException {
        return dao.getAllTransaction();
    }
}
