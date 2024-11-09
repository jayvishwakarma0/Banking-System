package org.project.bankingsystem.daoImpl;

import org.project.bankingsystem.dao.TransactionDao;
import org.project.bankingsystem.exception.TransactionException;
import org.project.bankingsystem.model.Account;
import org.project.bankingsystem.model.TransactionTab;
import org.project.bankingsystem.utility.BankUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {
    @Override
    public boolean deposit(int accountNumber,Double amount) throws TransactionException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            em.getTransaction().begin();

            Account account = em.find(Account.class, accountNumber);
            if (account == null) {
                throw new TransactionException("Account not found with account number: " + accountNumber);
            }

            account.setBalance(account.getBalance() + amount);

            TransactionTab transaction = new TransactionTab();
            transaction.setAccount(account);
            transaction.setAmount(amount);
            transaction.setTransactionDate(new Date());
            transaction.setTransactionType("DEPOSIT");

            em.persist(transaction);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new TransactionException("Error processing deposit: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean withdrawal(int accountNumber,Double amount) throws TransactionException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            em.getTransaction().begin();

            Account account = em.find(Account.class, accountNumber);
            if (account == null) {
                throw new TransactionException("Account not found with account number: " + accountNumber);
            }

            if (account.getBalance() < amount) {
                throw new TransactionException("Insufficient balance");
            }

            account.setBalance(account.getBalance() - amount);

            TransactionTab transaction = new TransactionTab();
            transaction.setAccount(account);
            transaction.setAmount(amount);
            transaction.setTransactionDate(new Date());
            transaction.setTransactionType("WITHDRAWAL");

            em.persist(transaction);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new TransactionException("Error processing withdrawal: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean transfer(int fromAccountNumber, int toAccountNumber, Double amount) throws TransactionException {
        EntityManager em =BankUtil.provideEntityManager();
        try {
            em.getTransaction().begin();

            Account fromAccount = em.find(Account.class, fromAccountNumber);
            Account toAccount = em.find(Account.class, toAccountNumber);

            if (fromAccount == null || toAccount == null) {
                throw new TransactionException("One or both accounts not found");
            }

            if (fromAccount.getBalance() < amount) {
                throw new TransactionException("Insufficient balance in the source account");
            }

            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);

            TransactionTab transaction = new TransactionTab();
            transaction.setAccount(fromAccount);
            transaction.setAmount(amount);
            transaction.setTransactionDate(new Date());
            transaction.setTransactionType("TRANSFER");

            em.persist(transaction);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new TransactionException("Error processing transfer: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Double getBalance(int accountNumber) throws TransactionException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            Account account = em.find(Account.class, accountNumber);
            if (account == null) {
                throw new TransactionException("Account not found with account number: " + accountNumber);
            }
            return account.getBalance();
        } catch (Exception e) {
            throw new TransactionException("Error retrieving balance: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    @Override
    public TransactionTab getTransactionsById(int transactionId) throws TransactionException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            TransactionTab transaction = em.find(TransactionTab.class, transactionId);
            if (transaction == null) {
                throw new TransactionException("Transaction not found with ID: " + transactionId);
            }
            return transaction;
        } catch (Exception e) {
            throw new TransactionException("Error retrieving transaction by ID: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public TransactionTab getTransactionByAccountId(int accountNumber) throws TransactionException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            String jpql = "SELECT t FROM TransactionTab t WHERE t.account.accountNumber = :accountNumber";
            TypedQuery<TransactionTab> query = em.createQuery(jpql, TransactionTab.class);
            query.setParameter("accountNumber", accountNumber);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new TransactionException("Transaction not found for account number: " + accountNumber);
        } catch (Exception e) {
            throw new TransactionException("Error retrieving transaction by account number: " + e.getMessage());
        } finally {
            em.close();
        }
    }


    @Override
    public List<TransactionTab> getAllTransaction() throws TransactionException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            String jpql = "SELECT t FROM TransactionTab t";
            TypedQuery<TransactionTab> query = em.createQuery(jpql, TransactionTab.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new TransactionException("Error retrieving all transactions: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
