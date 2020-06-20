package com.zzy.annotationDao;

import com.zzy.annotationDao.dynamicSQL.UserProvider;
import com.zzy.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.javassist.bytecode.SignatureAttribute;

import java.util.List;
import java.util.Map;

/**
 * @author zzy
 * @date 2020/6/20 10:30
 * 基于注解的sql查询
 */

public interface UserSelectMapper {

    /*
    * 查询单条数据,传递单个参数
    * */
    @Select("select * from `user` where id =#{id}")
    @Results({
            @Result(property = "id",column = "id",javaType = Integer.class),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password")
    })

    public User findOneUser(Integer id);

    //查询多条数据，传递对象

    @Results(id = "common",value = {
            @Result(property = "id",column = "id",javaType = Integer.class),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password")
    })
    @Select("select * from `user` where username=#{username} and password=#{password}")
    public List<User> findUsers(User user);

    //动态查询
    @SelectProvider(UserProvider.class)
    @ResultMap("common")  //引用其他resultmap的id
    public List<User> findUserByDynamic(User user);

    //返回map，指定id为主键
    @SelectProvider(type = UserProvider.class,method = "findUserByDynamic")
    @MapKey("username")   //将查询出的结果存储在map中，每条结果对应的key可以指定为他们中的一列，如果该列值有重复字段，则只会保留一条数据，可以用于结果去重
    @ResultMap("common")  //指定查询出来的结果集映射规则
    public Map<String,List<User>> findUsersByDynamicAndReturnMap(User user);

    //使用foreach动态sql
    //mysql中，in语句中参数个数是不限制的。不过对整段sql语句的长度有了限制（max_allowed_packet 单位是字节【B】）。默认是4M
    //Oracle中，in语句中可放的最大参数个数是1000个。之前遇到超过1000的情况，可用如下语句，但如此多参数项目会低，可考虑用别的方式优化。
    //要在带注解的映射器接口类中使用动态 SQL，可以使用 script 元素。比如:
    //foreach 在使用的注意，在遍历list后获取元素时一个对象，#{item.username}
    @Select("<script> " +
            "select * from `user` where username in" +
                "<foreach item='item'  index='index'  collection = 'list' open = '(' separator = ','  close = ')'>" +
                     "#{item.username}" +
                "</foreach>"+
            "</script>")
    @ResultMap("common")
    public List<User> findUsersByForeach(@Param("list") List<User> list);
}
