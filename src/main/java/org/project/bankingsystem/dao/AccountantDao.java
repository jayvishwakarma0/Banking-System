package org.project.bankingsystem.dao;

import org.project.bankingsystem.exception.AccountantException;
import org.project.bankingsystem.model.Accountant;

public interface AccountantDao {
     boolean validate(String username,String password) throws AccountantException;

    boolean register(Accountant accountant) throws AccountantException;
}
