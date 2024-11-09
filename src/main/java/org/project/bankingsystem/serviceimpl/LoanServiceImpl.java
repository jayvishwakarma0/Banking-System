package org.project.bankingsystem.serviceimpl;

import org.project.bankingsystem.dao.LoanDao;
import org.project.bankingsystem.daoImpl.LoanDaoImpl;
import org.project.bankingsystem.exception.LoanException;
import org.project.bankingsystem.model.Loan;
import org.project.bankingsystem.service.LoanService;

import java.util.List;

public class LoanServiceImpl implements LoanService {
    LoanDao dao = new LoanDaoImpl();
    @Override
    public boolean allocateLoan(int customerId,int accountNumber, Double amount) throws LoanException {
        return dao.allocateLoan(customerId,accountNumber, amount);
    }

    @Override
    public boolean updateLoan(int loanId, Double amount) throws LoanException {
        return dao.updateLoan(loanId, amount);
    }

    @Override
    public boolean deleteLoan(int loanId) throws LoanException {
        return dao.deleteLoan(loanId);
    }

    @Override
    public Loan getLoanById(int loanId) throws LoanException {
        return dao.getLoanById(loanId);
    }

    @Override
    public List<Loan> getAllLoans() throws LoanException {
        return dao.getAllLoans();
    }
}
