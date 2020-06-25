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
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
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
         sqlSession = build.openSession(true);
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
        for (int i = 0 ;i < 41 ;i++){
            User user = new User();
            user.setId(i);
            users.add(user);
        }
        List<User> findusers = mapper.findusers(users);
        System.out.println(findusers);
    }

    @Test
    public void  test3(){
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setId(39);
        User user1 = mapper.getUser(user);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sf.format(user1.getCreateTime());
        System.out.println(format);

    }

    @Test
    public void test() throws ParseException {
        //在插入操作中，需要自动提交事务或手动提交事务。
        /**
         * 在插入时间：
         * 1. 时间统一使用java.util.Date存储，则mybatis映射，根据数据存储类型装换为date或者timestamp类型
         * 1. 在使用中使用java.sql.Date  :DATE     java.sql.timestamp :TIMESTAMP
         * 两种方式都可以在插入，和查询中正常使用。
         */
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setId(42);
        user.setPassword("123");
        user.setUsername("345");
        user.setBirthady("2020-02-01");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sf.format(new Date());
        Date parse = sf.parse(format);
        user.setCreateTime(new java.sql.Date(new Date().getTime()));//数据库类型date，传递util.date，会自动截取util.date中的时间
        user.setCreateTime1(new Time(new Date().getTime()));

        user.setCreateTime2(new Timestamp(new Date().getTime()));
        mapper.insertUser1(user);

    }

    @Test
    public void test5(){
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setId(31);
        user.setPassword("123");
        user.setUsername("345");
        user.setBirthady("2020-02-01");
        mapper.insertUser(user);
    }
}
