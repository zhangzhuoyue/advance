import com.zzy.annotationDao.UserSelectMapper;
import com.zzy.dao.UserMapperDao;
import com.zzy.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zzy
 * @date 2020/6/20 10:34
 */
public class MybatisAnnotation {
   // private   final Logger logger = Logger.getLogger(MybatisAnnotation.class);
    public SqlSession sqlSession;
    SqlSessionFactory build;
    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlConfig.xml");
         build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession= build.openSession(true);
    }

    @Test
    public void findOneUser(){
        UserSelectMapper mapper = sqlSession.getMapper(UserSelectMapper.class);
        User oneUser = mapper.findOneUser(23);
        System.out.println(oneUser);
    }

    @Test
    public void findUsers(){
        UserSelectMapper mapper = sqlSession.getMapper(UserSelectMapper.class);
        User user = new User();
        user.setUsername("张张");
        user.setPassword("123gr");
        List<User> users = mapper.findUsers(user);
        System.out.println(users);
    }

    //动态sql查询
    @Test
    public void findUserByDynamic(){
        UserSelectMapper mapper = sqlSession.getMapper(UserSelectMapper.class);
        User user = new User();
        user.setUsername("张张");
        user.setPassword("123gr");
        List<User> users = mapper.findUserByDynamic(user);
        sqlSession.clearCache();
        //System.out.println(users);
        List<User> users1 = mapper.findUserByDynamic(user);
        System.out.println(users == users1);


    }
    //动态sql查询，使用map接受
    @Test
    public void findUsersByDynamicAndReturnMap(){
        UserSelectMapper mapper = sqlSession.getMapper(UserSelectMapper.class);
        User user = new User();
        user.setUsername("张张");
        user.setPassword("123gr");
        Map<String, List<User>> map = mapper.findUsersByDynamicAndReturnMap(user);
        //System.out.println(map);
        for (Map.Entry entry : map.entrySet()){
            System.out.println(entry.getKey()+"  "+entry.getValue());
        }
    }

    //动态sql foreach
    @Test
    public void findUsersByForeach(){
        UserSelectMapper mapper = sqlSession.getMapper(UserSelectMapper.class);
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 1000 ;i++){
            User user = new User();
            user.setUsername("张张");
            user.setPassword("123gr");
            list.add(user);
        }
        List<User> usersByForeach = mapper.findUsersByForeach(list);
        System.out.println(usersByForeach);

    }

    //测试二级缓存
    @Test
    public void namespaceCache() throws IOException {


        SqlSession sqlSession1 = build.openSession(true);
        SqlSession sqlSession2 = build.openSession(true);
        UserMapperDao mapper1 = sqlSession1.getMapper(UserMapperDao.class);
        UserMapperDao mapper2 = sqlSession2.getMapper(UserMapperDao.class);
        List<User> userOrders1 = mapper1.selectUser("张张");
        sqlSession1.close();//sqlsession查询完成后，将其关闭，关闭后会将一级缓存中数据清除，将数据缓存到二级
        List<User> userOrders2 = mapper2.selectUser("张张");
        System.out.println(userOrders1 );

    }
}
