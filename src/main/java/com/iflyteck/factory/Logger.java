package com.iflyteck.factory;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component("logger")
@Aspect // 表示当前类是一个切面类
public class Logger {

    @Pointcut("execution(* com.iflyteck.service.impl.*.*(..))")
    public void pt1 () {}

    /**
     * 前置通知
     */
    @Before("pt1()")
    public void beforeLog () {
        System.out.println("前置通知。。。。");
    }

    /**
     * 后置通知
     */
    @AfterReturning("pt1()")
    public void afterReturningLog () {
        System.out.println("后置通知。。。。");
    }

    /**
     * 异常通知
     */
    @AfterThrowing("pt1()")
    public void afterThrowingLog () {
        System.out.println("异常通知。。。。");
    }
    /**
     * 最终通知
     */
    @After("pt1()")
    public void afterLog () {
        System.out.println("最终通知。。。。");
    }

    /**
     * 环绕通知
     */
    // @Around("pt1()")
    public Object aroundLog (ProceedingJoinPoint proceedingJoinPoint) {
        Object reValue = null;
        try {
            Object[] args = proceedingJoinPoint.getArgs();
            System.out.println("环绕通知中的。。。。前置");
            reValue = proceedingJoinPoint.proceed(args);
            System.out.println("环绕通知中的。。。。前置");
            return reValue;
        } catch (Throwable throwable) {
            System.out.println("环绕通知中的。。。。异常");
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("环绕通知中的。。。。最终");
        }
    }
}
