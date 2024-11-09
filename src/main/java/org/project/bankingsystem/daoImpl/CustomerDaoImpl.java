package org.project.bankingsystem.daoImpl;

import org.project.bankingsystem.dao.CustomerDao;
import org.project.bankingsystem.exception.CustomerException;
import org.project.bankingsystem.model.Customer;
import org.project.bankingsystem.utility.BankUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean addCustomer(Customer customer) throws CustomerException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new CustomerException("Error adding customer: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean updateCustomer(Customer customer) throws CustomerException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(customer);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new CustomerException("Error updating customer: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean deleteCustomer(int customerId) throws CustomerException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, customerId);
            if (customer != null) {
                em.remove(customer);
                em.getTransaction().commit();
                return true;
            } else {
                em.getTransaction().rollback();
                throw new CustomerException("Customer not found with ID: " + customerId);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new CustomerException("Error deleting customer: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Customer getCustomerById(int customerId) throws CustomerException {
        EntityManager em = BankUtil.provideEntityManager();
        try{
            return em.find(Customer.class,customerId);
        }catch (NoResultException e) {
            throw new CustomerException("Customer not found with customer Id: " + customerId);
        } catch (Exception e) {
            throw new CustomerException("Error retrieving customer by customer Id: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Customer getCustomerByUsername(String username) throws CustomerException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            String jpql = "SELECT c FROM Customer c WHERE c.username = :username";
            TypedQuery<Customer> query = em.createQuery(jpql, Customer.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new CustomerException("Customer not found with username: " + username);
        } catch (Exception e) {
            throw new CustomerException("Error retrieving customer by username: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Customer> getAllCustomers() throws CustomerException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            String jpql = "SELECT c FROM Customer c";
            TypedQuery<Customer> query = em.createQuery(jpql, Customer.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new CustomerException("Error retrieving all customers: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean validate(String username, String password) throws CustomerException {
        EntityManager em = BankUtil.provideEntityManager();
        try {
            String jpql = "SELECT c FROM Customer c WHERE c.username = :username AND c.password = :password";
            TypedQuery<Customer> query = em.createQuery(jpql, Customer.class);
            query.setParameter("username", username);
            query.setParameter("password", password);

            Customer customer = query.getSingleResult();
            return customer != null;
        } catch (NoResultException e) {
            // No matching customer found; return false
            return false;
        } catch (Exception e) {
            throw new CustomerException("Error validating credentials: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
