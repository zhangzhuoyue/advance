import com.zzy.dao.UserMapperDao;
import com.zzy.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zzy
 * @date 2020/6/18 22:33
 */
public class MybatisCache {

    public SqlSession sqlSession;
    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = build.openSession(true);
    }

    @Test
    public void firstCache(){
        UserMapperDao mapper = sqlSession.getMapper(UserMapperDao.class);
        User userOrders = mapper.findUserOrders1(4);
        sqlSession.clearCache();
        User userOrders1 = mapper.findUserOrders1(4);

        System.out.println(userOrders == userOrders1);

    }
}
