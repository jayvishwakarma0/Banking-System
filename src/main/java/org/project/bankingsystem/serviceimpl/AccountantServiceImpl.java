package org.project.bankingsystem.serviceimpl;

import org.project.bankingsystem.dao.AccountantDao;
import org.project.bankingsystem.daoImpl.AccountantDaoImpl;
import org.project.bankingsystem.exception.AccountantException;
import org.project.bankingsystem.model.Accountant;
import org.project.bankingsystem.service.AccountantService;

public class AccountantServiceImpl implements AccountantService {
    AccountantDao dao = new AccountantDaoImpl();
    @Override
    public boolean validate(String username, String password) throws AccountantException {
        return dao.validate(username,password);
    }

    @Override
    public boolean register(Accountant accountant) throws AccountantException {
        return dao.register(accountant);
    }
}
