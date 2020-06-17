package com.zzy.dao;

import com.zzy.pojo.Order;
import com.zzy.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zzy
 * @date 2020/6/17 23:03
 */
public interface UserMapperDao {

    //增加用户
    @Insert("insert into `user` (id,username,password,birthady) value (#{id},#{username},#{password},#{birthady})")
    public int addUser(User user);

    @Select("select * from user where id=#{id}")
    public User selectUser(Integer id);

    @Update("update user set username=#{username},password=#{password} where id=#{id}")
    public int updateuser(User user);

    @Delete("delete from user where id =#{id}")
    public int deleteUserById(Integer id);

    /*根据用户查询订单 一对多关系*/
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "birthady",column = "birthady"),
            @Result(property = "order",column = "id，uid" ,javaType = Order.class//colimn是传递给下一条sql的参数,可以传递多个参数

            ,one = @One(select = "com.zzy.dao.OrderMapperDao.findOrderByuserid"))
    })
    @Select("select * from `user` where id =#{id}")
    /*在类明上加上``防止和数据库关键字冲突，另外注意使用的一对一关系还是一对多关系，如果在编写是返现类型不匹配
    * 可能是字段名不匹配，也可能是注解声明的是一对一，实体对象封装的是一对多，在陈关系不匹配*/
    public User findUserOrders(int i);
}
