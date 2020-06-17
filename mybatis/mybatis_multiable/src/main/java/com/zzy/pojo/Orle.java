package com.zzy.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzy
 * @date 2020/6/17 22:15
 */
public class Orle {

    public Integer id;
    public String rolename;
    public List<User> userList = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return "Orle{" +
                "id=" + id +
                ", rolename='" + rolename + '\'' +
                ", userList=" + userList +
                '}';
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

}
