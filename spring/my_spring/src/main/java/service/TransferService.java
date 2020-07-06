package service;

import java.sql.SQLException;

/**
 * @author zzy
 * @date 2020/7/1 23:15
 */
public interface TransferService {
    //转账
    void transfer(String fromCarNo, String toCarNo, int money) throws SQLException;
}
