package com.iflyteck.service.impl;

import com.iflyteck.dao.AccountDao;
import com.iflyteck.domain.Account;
import com.iflyteck.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public Account findOneAccount(Integer id) {
        return accountDao.findAccountById(id);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void transfer(String sourceName, String targetName, Float money) {
        Account sourceAccount = accountDao.findAccountByName(sourceName);
        Account targetAccount = accountDao.findAccountByName(targetName);
        sourceAccount.setMoney(sourceAccount.getMoney() - money);
        targetAccount.setMoney(targetAccount.getMoney() + money);
        accountDao.updateAccount(sourceAccount);
        accountDao.updateAccount(targetAccount);
    }
}
