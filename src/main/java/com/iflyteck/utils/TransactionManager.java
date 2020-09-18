package com.iflyteck.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;

    /**
     * 开启事务
     */
    public void beginTransaction () {
        try{
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 提交事务
     */
    public void commit () {
        try{
            connectionUtils.getThreadConnection().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 回滚事务
     */
    public void rollback () {
        try{
            connectionUtils.getThreadConnection().rollback();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 释放资源
     */
    public void realese () {
        try{
            connectionUtils.getThreadConnection().close();
            connectionUtils.removeConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
