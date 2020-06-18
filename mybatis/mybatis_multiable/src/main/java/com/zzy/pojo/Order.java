package com.zzy.pojo;

/**
 * @author zzy
 * @date 2020/6/16 22:09
 */

/*在一对一  一对所多  多对多关系 中，实体类中将不变的属性确定，将可变的对应关系泛型化，这样只需要根据传递的泛型就可以
* 改变关系，不需要为了不同关系写不同的实体类
* */
public class Order<T> {
    public Integer id;
    public String name;
    public String price;
    public T user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public T getUser() {
        return user;
    }

    public void setUser(T user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", user=" + user +
                '}';
    }
}
