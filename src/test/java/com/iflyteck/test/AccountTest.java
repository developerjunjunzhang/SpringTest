package com.iflyteck.test;

import com.iflyteck.config.SpringConfiguration;
import com.iflyteck.domain.Account;
import com.iflyteck.service.AccountService;
import com.iflyteck.service.impl.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountTest {

    @Autowired
    @Qualifier("accountServiceProxy")
    private AccountService accountService;

    @Test
    public void testFindAll () {
        List<Account> accounts = accountService.findAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne () {
        Account account = accountService.findOneAccount(1);
        System.out.println(account);
    }

    @Test
    public void testSave () {
        Account account = new Account();
        account.setName("test");
        account.setMoney(1234f);
        accountService.saveAccount(account);
    }

    @Test
    public void testUpdate () {
        Account account = accountService.findOneAccount(1);
        account.setName("mmm");
        accountService.updateAccount(account);
    }

    @Test
    public void testDelete () {
        accountService.deleteAccount(4);
    }

    @Test
    public void testTransfer () {
        accountService.transfer("aaa","bbb",100f);
    }
}
