package org.project.bankingsystem.service;

import org.project.bankingsystem.exception.LoanException;
import org.project.bankingsystem.model.Loan;

import java.util.List;

public interface LoanService {
    boolean allocateLoan(int cusomerId,int accountNumber, Double amount) throws LoanException;
    boolean updateLoan(int loanId, Double amount) throws LoanException;
    boolean deleteLoan(int loanId) throws LoanException;
    Loan getLoanById(int loanId) throws LoanException;
    List<Loan> getAllLoans() throws LoanException;
}
