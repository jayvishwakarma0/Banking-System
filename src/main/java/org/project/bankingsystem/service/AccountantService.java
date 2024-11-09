package org.project.bankingsystem.service;

import org.project.bankingsystem.exception.AccountantException;
import org.project.bankingsystem.model.Accountant;

public interface AccountantService {
    boolean validate(String username,String password) throws AccountantException;

    boolean register(Accountant accountant) throws AccountantException;
}
