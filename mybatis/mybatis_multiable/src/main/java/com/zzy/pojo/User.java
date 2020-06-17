package com.zzy.pojo;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;
import java.util.Objects;

/**
 * @author zzy
 * @date 2020/6/16 22:08
 */
public class User {
    public Integer id;
    public String username;
    public String password;
    public String birthady;
    //public List<Orle> orderList;
    public Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(birthady, user.birthady) &&
                Objects.equals(order, user.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, birthady, order);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthady='" + birthady + '\'' +
                ", order=" + order +
                '}';
    }
}
