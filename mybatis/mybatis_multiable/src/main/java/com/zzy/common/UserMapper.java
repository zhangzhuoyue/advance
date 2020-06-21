package com.zzy.common;

import com.zzy.pojo.TK_User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author zzy
 * @date 2020/6/21 17:28
 */
public interface UserMapper extends Mapper<TK_User> {

    @Select("select * from `user` where username =#{username}")
    public List<TK_User> sellectUsers(TK_User user);
}
