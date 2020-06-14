package test;

import io.IResources;
import org.junit.Test;
import pojo.User;
import sqlSession.SqlSession;
import sqlSession.SqlSessionFactory;
import sqlSession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class IPerenceTest {
    @Test
    public void test() throws Exception {
        //获取文件输入流
        InputStream resourceAsStream = IResources.getResourceAsStream("/config/databaseConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();

        List<Object> objects = sqlSession.selectList("product.selectList");

        System.out.println(objects);
        System.out.println("---------------------");
        User user = new User();
        user.setId(3);
        user.setUsername("zhang");
        Object o = sqlSession.selectOne("product.selectOne",user);
        System.out.println(o);
    }
}
