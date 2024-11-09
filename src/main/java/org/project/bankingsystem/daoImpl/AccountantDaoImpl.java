package org.project.bankingsystem.daoImpl;

import org.project.bankingsystem.dao.AccountantDao;
import org.project.bankingsystem.exception.AccountantException;
import org.project.bankingsystem.model.Accountant;
import org.project.bankingsystem.utility.BankUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class AccountantDaoImpl implements AccountantDao {
    @Override
    public boolean validate(String username, String password) throws AccountantException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            String jpql = "SELECT a FROM Accountant a WHERE a.username = :username AND a.password = :password";
            TypedQuery<Accountant> query = em.createQuery(jpql, Accountant.class);
            query.setParameter("username", username);
            query.setParameter("password", password);

            Accountant accountant = query.getSingleResult();
            return accountant != null;
        } catch (NoResultException e) {
            // No matching customer found; return false
            return false;
        } catch (Exception e) {
            throw new AccountantException("Error validating credentials: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean register(Accountant accountant) throws AccountantException {
        EntityManager em = BankUtil.provideEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(accountant);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            throw new AccountantException("Registration failed: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
