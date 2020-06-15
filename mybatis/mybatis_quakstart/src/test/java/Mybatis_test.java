import com.zzy.dao.UserDao;
import com.zzy.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zzy
 * @date 2020/6/15 21:08
 */
public class Mybatis_test {

    private SqlSession sqlSession;

    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
         sqlSession = build.openSession();
    }
    /*查询操作*/
    @Test
    public void test1() throws IOException {

        List<User> objects = sqlSession.selectList("usermapper.findAll");
        for (User user : objects){
            System.out.println(user);
        }
    }
    /*增*/
    @Test
    public void add(){
        User user = new User();
        user.setId(5);
        user.setUsername("张越");
        user.setPassword("zzz");
        user.setBirthady("2020-02-23");
        sqlSession.insert("usermapper.insertUser",user);
        sqlSession.commit();
    }

    /*改*/
    @Test
    public void update(){
        User user = new User();
        user.setId(5);
        user.setUsername("su张越");//com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Truncated incorrect DOUBLE value: '张越kk'
        user.setPassword("zzz");
        user.setBirthady("2020-09-23");
        sqlSession.update("usermapper.updateUser",user);
        sqlSession.commit();
    }
    /*删除*/
    @Test
    public void delete(){
        sqlSession.delete("usermapper.deleteUser",3);
        sqlSession.commit();
    }

    /*接口代理模式，使用jdk动态代理实现*/
    @Test
    public void dao(){
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> all = mapper.findAll();
        System.out.println(all);
    }

    /*多条件查询*/
    @Test
    public void findByCondition(){
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = new User();
        //user.setId(4);
        List<User> byCondition = mapper.findByCondition(user);
        System.out.println(byCondition);


    }

    //foreach
    @Test
    public void test2(){
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> users = new ArrayList<>();
        for (int i = 0 ;i < 20 ;i++){
            User user = new User();
            user.setId(i);
            users.add(user);
        }
        List<User> findusers = mapper.findusers(users);
        System.out.println(findusers);
    }
}
