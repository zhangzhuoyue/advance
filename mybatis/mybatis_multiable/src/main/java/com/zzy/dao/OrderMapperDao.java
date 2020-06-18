package com.zzy.dao;

import com.zzy.pojo.Order;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zzy
 * @date 2020/6/17 23:35
 */
public interface OrderMapperDao {

    @Select(("select * from `order` where id=#{id} and uid=#{uid}"))
    public Order findOrderByuserid(Integer id,Integer uid);




    /*
    * 在一对多关系中，该方法的返回值类型List<Order>，就是对应的User对象的T
    * */
    @Select("select * from `order` where id = #{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "price",column = "price")

    })
    public List<Order> findOrders(Integer id);
}
