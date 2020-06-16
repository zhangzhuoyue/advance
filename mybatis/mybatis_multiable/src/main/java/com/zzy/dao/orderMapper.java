package com.zzy.dao;

import com.zzy.pojo.Order;
import com.zzy.pojo.User;

import java.util.List;

/**
 * @author zzy
 * @date 2020/6/16 23:01
 */
public interface orderMapper {

    public User findUserAndOrder(int id);
}
