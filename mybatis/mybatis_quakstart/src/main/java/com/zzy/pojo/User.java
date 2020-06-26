package com.zzy.pojo;


import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * @author zzy
 * @date 2020/6/15 20:55
 */
public class User {

    private int id;
    private String username;
    private String password;
    private String birthady;
    private Date createTime;
    private java.util.Date createTime1;
    private Timestamp createTime2;

    public java.util.Date getCreateTime1() {
        return createTime1;
    }

    public void setCreateTime1(java.util.Date createTime1) {
        this.createTime1 = createTime1;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthady='" + birthady + '\'' +
                ", createTime=" + createTime +
                ", createTime1=" + createTime1 +
                ", createTime2=" + createTime2 +
                '}';
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Timestamp getCreateTime2() {
        return createTime2;
    }

    public void setCreateTime2(Timestamp createTime2) {
        this.createTime2 = createTime2;
    }
}
