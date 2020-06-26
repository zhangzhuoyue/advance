package com.zzy.dao;

import com.zzy.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    //根据时间查询用户信息
    public  List<Map<String,String>> findUserByTime(Map<String, Date> map);

    //根据字符串时间查询用户信息
    public List<Map<String,Object>> findUserByStringTime(@Param("beginDate") String beginDate,@Param("endDate") String endDate);

    //关于数据库字段类型是时间戳，传递java.util.Date插叙
    public List<User> findUsersByTimestamp(@Param("beginDate") Date beginDate,@Param("endDate") Date endDate);


    //findUsersByTimestampString
    public List<User> findUsersByTimestampString(@Param("beginDate") String beginDate,@Param("endDate") String endDate);

    public Map<String,Object> findByMap(@Param("map") Map<String,Object> map);
}
