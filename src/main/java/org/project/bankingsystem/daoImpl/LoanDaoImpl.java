package org.project.bankingsystem.daoImpl;

import org.project.bankingsystem.dao.LoanDao;
import org.project.bankingsystem.exception.LoanException;
import org.project.bankingsystem.model.Account;
import org.project.bankingsystem.model.Customer;
import org.project.bankingsystem.model.Loan;
import org.project.bankingsystem.utility.BankUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class LoanDaoImpl implements LoanDao {
    @Override
    public boolean allocateLoan(int customerId,int accountNumber, Double amount) throws LoanException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            em.getTransaction().begin();

            // Retrieve the account to link with the loan
            Customer customer  = em.find(Customer.class,customerId);
            Account account = em.find(Account.class, accountNumber);
            if (account == null) {
                throw new LoanException("Account not found with account number: " + accountNumber);
            }

            // Create a new Loan and associate it with the retrieved account
            Loan loan = new Loan();
            loan.setCustomer(customer);
            loan.setAmount(amount);
            loan.setAccount(account);
            em.persist(loan);

            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new LoanException("Error allocating loan: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean updateLoan(int loanId, Double amount) throws LoanException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            em.getTransaction().begin();

            Loan loan = em.find(Loan.class, loanId);
            if (loan == null) {
                throw new LoanException("Loan not found with ID: " + loanId);
            }
            // Update loan amount
            loan.setAmount(amount);
            em.merge(loan);

            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new LoanException("Error updating loan: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean deleteLoan(int loanId) throws LoanException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            em.getTransaction().begin();

            Loan loan = em.find(Loan.class, loanId);
            if (loan != null) {
                em.remove(loan);
                em.getTransaction().commit();
                return true;
            } else {
                em.getTransaction().rollback();
                throw new LoanException("Loan not found with ID: " + loanId);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new LoanException("Error deleting loan: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Loan getLoanById(int loanId) throws LoanException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            Loan loan = em.find(Loan.class, loanId);
            if (loan == null) {
                throw new LoanException("Loan not found with ID: " + loanId);
            }
            return loan;
        } catch (Exception e) {
            throw new LoanException("Error retrieving loan by ID: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Loan> getAllLoans() throws LoanException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            String jpql = "SELECT l FROM Loan l";
            TypedQuery<Loan> query = em.createQuery(jpql, Loan.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new LoanException("Error retrieving all loans: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
