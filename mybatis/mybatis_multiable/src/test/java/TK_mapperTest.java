import com.zzy.common.UserMapper;
import com.zzy.pojo.TK_User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author zzy
 * @date 2020/6/21 17:13
 */
public class TK_mapperTest {

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
    public void test(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        TK_User tk_user = new TK_User();
      // tk_user.setId(27);
        tk_user.setUsername("张张");
        //tk_user.setPassword("234");
        //TK_User tk_user1 = mapper.selectOne(tk_user);//查询单个结果，必须加上主键id
        //查询所有结果
        List<TK_User> select = mapper.select(null);//null查询表所有数据，不为null，则根据主键id，和其他条件查询单个结果
        int i = mapper.selectCount(tk_user);
        TK_User tk_user1 = mapper.selectByPrimaryKey(23);//根据主键查询条件
       // mapper.insert(tk_user);  //插入数据，null值也会保存，不会使用数据库默认值
        //mapper.insertSelective(tk_user);//保存实体，null值不会保存，会使用数据库默认数据
        //mapper.updateByPrimaryKey(tk_user);  //根据主键更新实体所有字段，null值也会被更新
        //mapper.updateByPrimaryKeySelective(tk_user);//根据主键跟新实体字段，null值不会被更新
       // System.out.println(tk_user1);
        List<TK_User> tk_users1 = mapper.sellectUsers(tk_user);
        System.out.println(tk_users1);
        //example方法   ，进行数据库单表查询
        Example example = new Example(TK_User.class);
        //example.createCriteria().andEqualTo("id",1);
        example.createCriteria().andLike("password","123%");
        List<TK_User> tk_users = mapper.selectByExample(example);
        System.out.println(tk_users);
    }

}
