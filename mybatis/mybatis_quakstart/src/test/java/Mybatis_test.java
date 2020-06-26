import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import com.zzy.dao.UserDao;
import com.zzy.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;

/**
 * @author zzy
 * @date 2020/6/15 21:08
 */
public class Mybatis_test {
    private   final Logger logger = Logger.getLogger(Mybatis_test.class);

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
        user.setId(46);
        user.setPassword("123");
        user.setUsername("345");
        user.setBirthady("2020-02-01");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sf.format(new Date());
        Date parse = sf.parse(format);
        user.setCreateTime(new java.sql.Date(new Date().getTime()));//数据库类型date，传递util.date，会自动截取util.date中的时间
        user.setCreateTime1(new Date());

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

    //根据时间查询条件，传递一个java.util.Date,查询时间范围的内容。
    @Test
    public void test6(){
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE,-1);
        Date calTime = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(format.format(calTime));
        Map<String,Date> map = new HashMap<>();
        map.put("beginTime",calTime);//数据库字段是date，但是传入date，mybatis映射会将date截取日期部分。
        map.put("endTime",new Date());
        List<Map<String, String>> userByTime = mapper.findUserByTime(map);
        for (Map<String,String> map1 : userByTime){
            String password = map1.get("password");
            String username = map1.get("username");
            System.out.println(username  +"    "+ password);
        }
    }

    //传递字符串查询时间范围，数据库函数转换
    @Test
    public void test9(){
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<Map<String, Object>> userByStringTime = mapper.findUserByStringTime("2020-06-23", "2020-06-25");
        for (Map<String,Object> map : userByStringTime){
            for (Map.Entry<String,Object> entry : map.entrySet()){
                System.out.println(entry.getKey()  +"   "+entry.getValue());
            }
        }
    }

    //根据时间戳查询，传递参数类型是Date类型,可以查询
    @Test
    public void test10(){
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE,-1);
        Date lastDate = cal.getTime();
        Date nowDate = new Date();
        List<User> users = userDao.findUsersByTimestamp(lastDate, nowDate);
        for (User user : users){
            System.out.println(user);
            //从时间戳中获取时间和日期
            Timestamp createTime2 = user.getCreateTime2();
            long time = createTime2.getTime();
            java.sql.Date date = new java.sql.Date(time);
            Calendar instance = Calendar.getInstance();
            instance.setTime(new Date(time));
            int hour = instance.get(Calendar.HOUR_OF_DAY);
            int minute = instance.get(Calendar.MINUTE);
            int second = instance.get(Calendar.SECOND);
            String time1 = hour+":"+minute+":"+second;
            System.out.println(date);
            System.out.println(time1);
        }
    }

    //
    @Test
    public void test11(){
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> usersByTimestampString = userDao.findUsersByTimestampString("2020-06-24 08:12:12", "2020-06-26 23:23:23");

        ArrayList k = new ArrayList();

        for (User user : usersByTimestampString){
            System.out.println(user);
        }
    }

    @Test
    public void test12(){
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        Map<String,Object> map = new HashMap<>();
        map.put("id",43);
        Map<String, Object> byMap = mapper.findByMap(map);
        System.out.println(byMap);
    }



}
