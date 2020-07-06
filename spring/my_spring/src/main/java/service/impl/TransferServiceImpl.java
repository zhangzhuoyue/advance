package service.impl;

import dao.AccountDao;
import dao.impl.AccountDaoImpl;
import factory.FactoryBean;
import pojo.Account;
import service.TransferService;

import java.sql.SQLException;

/**
 * @author zzy
 * @date 2020/7/1 23:17
 */
public class TransferServiceImpl implements TransferService {

    private AccountDao accountDao ;

    //该方法用于注入AccountDao实现咧


    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void transfer(String fromCarNo, String toCarNo, int money) throws SQLException {
        Account from = accountDao.queryAccountByCarNo(fromCarNo);
        Account to = accountDao.queryAccountByCarNo(toCarNo);

        from.setMoney(from.getMoney()-money);
        to.setMoney(to.getMoney()+money);

        accountDao.updateAccountByCarNo(from);
        accountDao.updateAccountByCarNo(to);

    }
}
