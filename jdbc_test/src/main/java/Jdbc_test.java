import com.sun.xml.internal.bind.v2.util.DataSourceSource;

import java.sql.*;

public class Jdbc_test {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //通过驱动类获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://192.168.249.153:3306/mybatis?characterEncoding=utf-8", "root", "123456-abc");
            //定义sql语句，？是占位符
            String sql = "select * from user where id = ?";
            //获取预处理statement
            statement = connection.prepareStatement(sql);
            //设置参数值，第一个参数是sql语句中参数的序号（从1开始），第二个参数是参数值
            statement.setString(1,"3");
            //向数据库发送sql进行查询，获取结果集
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String birthady = resultSet.getString("birthady");
                User user = new User();
                user.setBirthday(birthady);
                user.setId(id);
                user.setPassword(password);
                user.setUsername(username);
                System.out.println(user.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
