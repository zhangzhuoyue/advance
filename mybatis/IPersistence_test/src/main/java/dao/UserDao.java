package dao;

import pojo.User;

import java.util.List;

/**
 * @author zzy
 * @date 2020/6/14 17:12
 */
public interface UserDao {

    /**
     * 查询学生表所有信息
     */
    public List<User> findAll();

    //根据条件查询学生信息
    public User findStudentByCondition(User user);

    //更新操作
    public void updateUser(User user);

    //删除
    public void deleteUser(User user);
    public void insertUser(User user);
}
