package com.zzy.pojo;

import java.util.List;

/**
 * @author zzy
 * @date 2020/6/16 22:08
 */
public class User {
    public Integer id;
    public String username;
    public String password;
    public String birthady;
    public List<Order> orderList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthady() {
        return birthady;
    }

    public void setBirthady(String birthady) {
        this.birthady = birthady;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthady='" + birthady + '\'' +
                ", orderList=" + orderList +
                '}';
    }
}
