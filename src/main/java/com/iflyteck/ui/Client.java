package com.iflyteck.ui;

import com.iflyteck.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        AccountService accountService = (AccountService)ac.getBean("accountServiceImpl");
        // accountService.saveAccount();
    }
}
