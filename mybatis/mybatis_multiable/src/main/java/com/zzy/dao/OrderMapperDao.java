package com.zzy.dao;

import com.zzy.pojo.Order;
import org.apache.ibatis.annotations.Select;

/**
 * @author zzy
 * @date 2020/6/17 23:35
 */
public interface OrderMapperDao {

    @Select(("select * from `order` where id=#{id} and uid=#{uid}"))
    public Order findOrderByuserid(Integer id,Integer uid);
}
