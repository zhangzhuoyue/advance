package dao;

import pojo.Account;

import java.sql.SQLException;

/**
 * @author zzy
 * @date 2020/7/1 22:34
 */
public interface AccountDao {

    //根据卡号查询账户
    Account queryAccountByCarNo(String carNo) throws SQLException;

    //根据看好更新账户
    int updateAccountByCarNo(Account account) throws SQLException;
}
