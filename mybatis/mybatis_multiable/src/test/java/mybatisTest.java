import com.zzy.dao.UserMapper;
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
        sqlSession = build.openSession();
    }

    @Test
    public void test(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
       User user = mapper.findOrders(4);
        System.out.println(user);
    }

    @Test
    public void test1(){
        orderMapper mapper = sqlSession.getMapper(orderMapper.class);
        User users = mapper.findUserAndOrder(4);
        System.out.println(users);
    }

}
