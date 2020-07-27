package com.iflyteck.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class ConnectionUtils {
    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    @Autowired
    private DataSource dataSource;

    /**
     * 获取当前线程上的连接
     * @return
     */
    public Connection getThreadConnection () {
        Connection connection = tl.get();
        try {
            if (connection == null) {
                connection = dataSource.getConnection();
                tl.set(connection);
            }
            return connection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection () {
        tl.remove();
    }
}
