package utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zzy
 * @date 2020/7/4 15:30
 */
//获取线程池中的连接
public class ConnectionUtils {

    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();
    //从threadlocal中获取连接，如果没有则从连接池中获取
    public Connection getCurrentThreadConnection() throws SQLException {
        Connection connection = connectionThreadLocal.get();
        if (connection == null){
            connectionThreadLocal.set(DruidUtils.getInstance().getConnection());
        }
        return connection;
    }
}
