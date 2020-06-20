import com.zzy.dao.UserMapper;
import com.zzy.dao.UserMapperDao;
import com.zzy.dao.orderMapper;
import com.zzy.pojo.Order;
import com.zzy.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author zzy
 * @date 2020/6/16 22:22
 */
public class mybatisTest {
    public SqlSession sqlSession;
    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = build.openSession(true);
    }

    @Test
    public void test(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//       User user = mapper.findOrders(4);
//        System.out.println(user);
    }

    @Test
    public void test1(){
//        orderMapper mapper = sqlSession.getMapper(orderMapper.class);
//        User users = mapper.findUserAndOrder(4);
        //System.out.println(users);
    }

    @Test
    public void test12(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
       // List<User> users = mapper.findUserAndOrleAll();
      //  System.out.println(users);
    }

    @Test
    public void insert(){
        UserMapperDao mapper = sqlSession.getMapper(UserMapperDao.class);
        User user = new User();
        user.setId(20);
        user.setUsername("张张");
        user.setPassword("123gr");
        int i = mapper.addUser(user);
        System.out.println(i);
        System.out.println(user.getId());
    }

    @Test
    public void select(){
        UserMapperDao mapper = sqlSession.getMapper(UserMapperDao.class);
        User user = new User();

        List<User> i = mapper.selectUser("3");
        System.out.println(i);
    }

    @Test
    public void delete(){
        UserMapperDao mapper = sqlSession.getMapper(UserMapperDao.class);
        User user = new User();
        user.setId(4);
        user.setUsername("张3333张");
        user.setPassword("123333333gr");

        int i = mapper.deleteUserById(3);
        System.out.println(i);
    }

    @Test
    public void update(){
        UserMapperDao mapper = sqlSession.getMapper(UserMapperDao.class);
        User user = new User();
        user.setId(4);
        user.setUsername("张3333张");
        user.setPassword("123333333gr");

        int i = mapper.updateuser(user);
        System.out.println(i);
    }

    @Test
    public void oneToOne(){
        UserMapperDao mapper = sqlSession.getMapper(UserMapperDao.class);

       User userOrders = mapper.findUserOrders1(1);
        System.out.println(userOrders);
    }

    @Test
    public void manyToMang(){
        UserMapperDao mapper = sqlSession.getMapper(UserMapperDao.class);
        User<Order> orderUser = mapper.finduserOrders2(4);
        System.out.println(orderUser);
    }

    @Test
    public void test4(){

    }


}
