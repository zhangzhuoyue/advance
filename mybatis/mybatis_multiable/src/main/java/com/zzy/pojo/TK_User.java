package com.zzy.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zzy
 * @date 2020/6/21 17:15
 */
@Table(name = "user")
public class TK_User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String username;
    public String password;
    public String birthady;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return "TK_User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthady='" + birthady + '\'' +
                '}';
    }
}
