package sqlSession;

import pojo.Configuration;
import pojo.MapperStatement;

import java.util.List;

/**
 * @author zzy
 * @date 2020/6/13 14:34
 */
public class DefaultSqlSession implements SqlSession{

    public Configuration configuration;
    public DefaultSqlSession(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        SimpleExecutor executor = new SimpleExecutor();
        MapperStatement statement = configuration.getStatement().get(statementId);
        //将底层的jdbc查询封装在对象中，直接调用
        List<Object> list = executor.query(configuration, statement, params);
        return (List<E>)list;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<Object> objects = selectList(statementId, params);
        if (objects.size() == 1){
            return (T)objects.get(0);
        }else {
            throw  new RuntimeException("查询结果为空");
        }

    }
}
