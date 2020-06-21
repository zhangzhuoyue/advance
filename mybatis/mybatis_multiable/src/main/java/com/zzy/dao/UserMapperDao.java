package com.zzy.dao;

import com.zzy.pojo.Order;
import com.zzy.pojo.User;
import javafx.collections.ListChangeListener;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.cache.decorators.LruCache;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.apache.ibatis.mapping.FetchType;
import org.mybatis.caches.redis.RedisCache;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author zzy
 * @date 2020/6/17 23:03
 */
@CacheNamespace(implementation = PerpetualCache.class) //使用mybatis默认的二级缓存方式缓存
   // @CacheNamespace(implementation = RedisCache.class)
public interface UserMapperDao {

    //增加用户
    @Insert({"insert into `user` (id,username,password,birthady) value (#{id},#{username},#{password},#{birthady})" })
    @Options(useGeneratedKeys = true, keyProperty = "id")//拿到数据库的自增主键，然后回填主键。
    /*@SelectKey(statement = "select max(id) from  `user`" ,keyProperty = "id" ,before = false ,resultType = Integer.class)
    在执行完插入后获取主键，主键值注入到入参中的属性
    */
    @SelectKey(statement = "select if(max(id)=null,1,max(id)+1) as id from `user` ",keyProperty = "id" ,before = true,resultType = Integer.class)
   /* @SelectKey(statement = "select if(max(id)=null,1,max(id)+1) as id from `user` ",keyProperty = "id" ,before = true,resultType = Integer.class)
   * 首先查询出表的主键，然后将主键注入到参数中，然后执行插入，适用 自定义主键
   * */
    public int addUser(User user);

    @Select("select * from user where username=#{username}")
    @Options(useCache = false,flushCache= Options.FlushCachePolicy.TRUE,timeout = 100,fetchSize = 2)
    public List<User> selectUser(String username);

    @Update("update user set username=#{username},password=#{password} where id=#{id}")
    public int updateuser(User user);

    @Delete("delete from user where id =#{id}")
    public int deleteUserById(Integer id);

    /*根据用户查询订单 一对一关系*/
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "birthady",column = "birthady"),
            @Result(property = "order",column = "id，uid" ,javaType = Order.class//colimn是传递给下一条sql的参数,可以传递多个参数

            ,one = @One(select = "com.zzy.dao.OrderMapperDao.findOrderByuserid" ,fetchType= FetchType.LAZY))

    })
    @Select("select * from `user` where id =#{id}")
    /*在类明上加上``防止和数据库关键字冲突，另外注意使用的一对一关系还是一对多关系，如果在编写是返现类型不匹配
    * 可能是字段名不匹配，也可能是注解声明的是一对一，实体对象封装的是一对多，在陈关系不匹配*/
    public User findUserOrders1(int i);


    /*一对多查询，一个用户对应多个订单*/
    @Results({
            @Result(property = "id" ,column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "order",column = "id" ,javaType = List.class,
            many = @Many(select = "com.zzy.dao.OrderMapperDao.findOrders"))
    })
    @Select("select * from user where id=#{id}")
    public  User<Order> finduserOrders2(Integer id);
}
