package com.zzy.dao;

import com.zzy.pojo.User;

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
}
