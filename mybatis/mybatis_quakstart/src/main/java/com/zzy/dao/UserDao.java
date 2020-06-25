package com.zzy.dao;

import com.zzy.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**A
 * @author zzy
 * @date 2020/6/15 23:10
 */
public interface UserDao {

    public List<User> findAll();

    //多条件查询
    public List<User> findByCondition(User user);

    //foreach
    public List<User> findusers(List<User> user);

    //测试java 时间和数据库时间对应关系
    public User getUser(User user);

    //插入数据
    public void insertUser1( User user);
    public void  insertUser(User user);
}
