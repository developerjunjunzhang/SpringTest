package com.iflyteck.factory;

import com.iflyteck.service.AccountService;
import com.iflyteck.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class BeanFactory {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionManager transactionManager;

    // 获取service的代理对象
    @Bean("accountServiceProxy")
    public AccountService getAccountService () {
        AccountService accountServiceProxy = (AccountService)Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object rtValue = null;
                try {
                    transactionManager.beginTransaction();
                    rtValue = method.invoke(accountService, args);
                    transactionManager.commit();
                    return rtValue;
                } catch (Exception e) {
                    transactionManager.rollback();
                    throw new RuntimeException(e);
                } finally {
                    transactionManager.realese();
                }
            }
        });
        return accountServiceProxy;
    }
}