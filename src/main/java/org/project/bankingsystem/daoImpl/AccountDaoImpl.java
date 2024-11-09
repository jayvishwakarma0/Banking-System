package org.project.bankingsystem.daoImpl;

import org.project.bankingsystem.dao.AccountDao;
import org.project.bankingsystem.exception.AccountException;
import org.project.bankingsystem.model.Account;
import org.project.bankingsystem.utility.BankUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    @Override
    public boolean createAccount(Account account) throws AccountException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(account);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AccountException("Error creating account: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean updateAccount(Account account) throws AccountException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(account);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AccountException("Error updating account: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean deleteAccount(int accountNumber) throws AccountException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            em.getTransaction().begin();
            Account account = em.find(Account.class, accountNumber);
            if (account != null) {
                em.remove(account);
                em.getTransaction().commit();
                return true;
            } else {
                em.getTransaction().rollback();
                throw new AccountException("Account not found with account number: " + accountNumber);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AccountException("Error deleting account: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Account getAccountByAccountNumber(int accountNumber) throws AccountException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            return em.find(Account.class, accountNumber);
        } catch (Exception e) {
            throw new AccountException("Error retrieving account by account number: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Account getAccountByCustomerId(int customerId) throws AccountException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            String jpql = "SELECT a FROM Account a WHERE a.customer.id = :customerId";
            TypedQuery<Account> query = em.createQuery(jpql, Account.class);
            query.setParameter("customerId", customerId);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new AccountException("Account not found for customer ID: " + customerId);
        } catch (Exception e) {
            throw new AccountException("Error retrieving account by customer ID: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Account> getAllAccounts() throws AccountException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            String jpql = "SELECT a FROM Account a";
            TypedQuery<Account> query = em.createQuery(jpql, Account.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new AccountException("Error retrieving all accounts: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
