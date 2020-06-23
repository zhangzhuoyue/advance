package pojo;

/**
 * @author zzy
 * @date 2020/6/13 10:38
 */
public class User {

    /**
     * mybatis使用反射和内省技术，将数据库字段和实体对象中属性进行封装
     * 如果使用mybatis默认的映射封装技术，则数据库查询出的字段名要和实体对象属性相同【本质反射技术在复制使用setxx方法，只要set后面的名字和数据库匹配即可】，否在无法映射，表现为成员属性一直是默认值
     * 对象的成员类型要和数据库字段类型对应，否则报错java.lang.IllegalArgumentException: argument type mismatch，【本质原因是通过invoke调用setxx方法进行赋值值参数类型不同】
     */

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
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthady='" + birthady + '\'' +
                '}';
    }
}
