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
        //获取文件输入流databaseConfig.xml
        /**
         * 由于maven打包会把 src/main/java 和 src/main/resources 下的文件放到 target/classes 下，所以下面统一以根路径代表此目录，总结起来有以下几个规律：
         *
         * Class.getResource()的资源获取如果以 / 开头，则从根路径开始搜索资源。
         * Class.getResource()的资源获取如果不以 / 开头，则从当前类所在的路径开始搜索资源。
         * ClassLoader.getResource()的资源获取不能以 / 开头，统一从根路径开始搜索资源。      ****使用中
         *
         * getResourceAsStream读取的文件路径只局限与工程的源文件夹中，包括在工程src根目录下，以及类包里面任何位置，但是如果配置文件路径是在除了源文件夹之外的其他文件夹中时，该方法是用不了的。
         * ————————————————
         * 版权声明：本文为CSDN博主「Captain249」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
         * 原文链接：https://blog.csdn.net/Captain249/article/details/98172911
         */
        InputStream resourceAsStream = IResources.getResourceAsStream("config/databaseConfig.xml");
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

    @Test
    public void update(){
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setUsername("zhang");
        user.setId(20);
       mapper.updateUser(user);



    }

    @Test
    public void delete(){
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setUsername("zhang");
        user.setId(19);
        mapper.deleteUser(user);



    }

    @Test
    public void insert(){
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setUsername("zhang");
        user.setId(19);
        user.setPassword("123123");
        user.setBirthady("2020-09-23");
        mapper.insertUser(user);



    }


}
