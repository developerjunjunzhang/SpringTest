package com.iflyteck.service.impl;

import com.iflyteck.dao.AccountDao;
import com.iflyteck.domain.Account;
import com.iflyteck.service.AccountService;
import com.iflyteck.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TransactionManager transactionManager;

    public List<Account> findAllAccount() {
        try {
            // 开启事务
            transactionManager.beginTransaction();
            // 执行操作
            List<Account> accounts = accountDao.findAllAccount();
            // 提交事务
            transactionManager.commit();
            // 返回结果
            return accounts;
        } catch (Exception e) {
            transactionManager.rollback();
            throw new RuntimeException(e);
        } finally {
            transactionManager.realese();
        }
    }

    public Account findOneAccount(Integer id) {
        try {
            // 开启事务
            transactionManager.beginTransaction();
            // 执行操作
            Account account = accountDao.findAccountById(id);
            // 提交事务
            transactionManager.commit();
            // 返回结果
            return account;
        } catch (Exception e) {
            transactionManager.rollback();
            throw new RuntimeException(e);
        } finally {
            transactionManager.realese();
        }
    }

    public void saveAccount(Account account) {
        try {
            // 开启事务
            transactionManager.beginTransaction();
            // 执行操作
             accountDao.saveAccount(account);
            // 提交事务
            transactionManager.commit();
            // 返回结果
        } catch (Exception e) {
            transactionManager.rollback();
            throw new RuntimeException(e);
        } finally {
            transactionManager.realese();
        }
    }

    public void updateAccount(Account account) {
        try {
            // 开启事务
            transactionManager.beginTransaction();
            // 执行操作
            accountDao.updateAccount(account);
            // 提交事务
            transactionManager.commit();
            // 返回结果
        } catch (Exception e) {
            transactionManager.rollback();
            throw new RuntimeException(e);
        } finally {
            transactionManager.realese();
        }
    }

    public void deleteAccount(Integer id) {
        try {
            // 开启事务
            transactionManager.beginTransaction();
            // 执行操作
            accountDao.deleteAccount(id);
            // 提交事务
            transactionManager.commit();
            // 返回结果
        } catch (Exception e) {
            transactionManager.rollback();
            throw new RuntimeException(e);
        } finally {
            transactionManager.realese();
        }
    }

    public void transfer(String sourceName, String targetName, Float money) {
        try {
            // 开启事务
            transactionManager.beginTransaction();
            // 执行操作
            Account sourceAccount = accountDao.findAccountByName(sourceName);
            Account targetAccount = accountDao.findAccountByName(targetName);
            sourceAccount.setMoney(sourceAccount.getMoney() - money);
            targetAccount.setMoney(targetAccount.getMoney() + money);
            accountDao.updateAccount(sourceAccount);
            accountDao.updateAccount(targetAccount);
            // 提交事务
            transactionManager.commit();
            // 返回结果
        } catch (Exception e) {
            transactionManager.rollback();
            throw new RuntimeException(e);
        } finally {
            transactionManager.realese();
        }
    }
}
