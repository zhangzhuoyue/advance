package test;

import dao.UserDao;
import io.IResources;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;
import pojo.User;
import sqlSession.SqlSession;
import sqlSession.SqlSessionFactory;
import sqlSession.SqlSessionFactoryBuilder;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;

/**
 * @author zzy
 * @date 2020/6/14 17:59
 */
public class IPerenceTest_dao {
    public SqlSession sqlSession;
    @Before
    public void before() throws PropertyVetoException, DocumentException {
        //查询所有用户
        //获取文件输入流
        InputStream resourceAsStream = IResources.getResourceAsStream("/databaseConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
        sqlSession = sqlSessionFactory.openSqlSession();
    }

    @Test
    public void test(){
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> all = mapper.findAll();
        System.out.println(all);
        User user = new User();
        user.setUsername("zhang");
        user.setId(3);
        User studentByCondition = mapper.findStudentByCondition(user);
        System.out.println(studentByCondition);


    }
}
