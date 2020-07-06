package utils;

import java.sql.SQLException;

/**
 * @author zzy
 * @date 2020/7/4 15:39
 */
public class TransactionManager {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    //开始手动开启事务
    public void beginTransaction() throws SQLException {
        connectionUtils.getCurrentThreadConnection().setAutoCommit(false);
    }

    //提交事务
    public void commit() throws SQLException {
        connectionUtils.getCurrentThreadConnection().commit();
    }

    //回滚事务
    public void rollback() throws SQLException {
        connectionUtils.getCurrentThreadConnection().rollback();
    }
}
