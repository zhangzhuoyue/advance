package dao.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import dao.AccountDao;
import pojo.Account;
import utils.ConnectionUtils;
import utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zzy
 * @date 2020/7/1 22:36
 */
public class AccountDaoImpl implements AccountDao {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    @Override
    public Account queryAccountByCarNo(String carNo) throws SQLException {
        //获取连接
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            Connection currentThreadConnection = connectionUtils.getCurrentThreadConnection();
            String sql = "select * from account where carNo=?";
            statement = currentThreadConnection.prepareStatement(sql);
            statement.setString(1, carNo);
            resultSet = statement.executeQuery();

            Account account = new Account();

            while (resultSet.next()) {
                account.setCarNo(resultSet.getString("carNo"));
                account.setName(resultSet.getString("name"));
                account.setMoney(resultSet.getInt("money"));
            }

            return account;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            statement.close();
        }
        return null;
    }

    @Override
    public int updateAccountByCarNo(Account account) throws SQLException {
        Connection currentThreadConnection = connectionUtils.getCurrentThreadConnection();
        String sql = "update student set money=? where carmo = ?";
        PreparedStatement statement = currentThreadConnection.prepareStatement(sql);
        int i = statement.executeUpdate();
        statement.close();
        //currentThreadConnection.close();
        return i;
    }


}
