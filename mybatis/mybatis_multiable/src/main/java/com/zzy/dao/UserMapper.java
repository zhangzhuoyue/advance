package com.zzy.dao;

import com.zzy.pojo.Order;
import com.zzy.pojo.User;

import java.util.List;

/**
 * @author zzy
 * @date 2020/6/16 22:11
 */
public interface UserMapper {

    public User findOrders(int id);
}
