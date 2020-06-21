import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import java.util.List;

/**
 * @author zzy
 * @date 2020/6/21 15:24
 */
public class PageHelperTest {

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
    public void pageHelper(){
        UserMapperDao userMapperDao = sqlSession.getMapper(UserMapperDao.class);
        PageHelper.startPage(4,2);
        List<User> users = userMapperDao.selectUser("张张");
        for(User user : users){
            System.out.println(user.toString());
        }

        PageInfo<User> pageInfo = new PageInfo<>(users);
        long total = pageInfo.getTotal();
        int lastPage = pageInfo.getLastPage();
        System.out.println(total);
        System.out.println(lastPage);
    }
}
