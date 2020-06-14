package sqlSession;

import pojo.Configuration;
import pojo.MapperStatement;

import java.lang.reflect.*;
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

    /**
     * 使用jdk动态代理为dao接口生成代理类
     * @param mapperClass
     * @param <T>
     * @return
     */
    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //底层调用的jdbc   //根据情况调用selectlist 或者selectOne
            //要定位调用哪一个方法，需要statementid ：sql唯一标识 namespace+id
                // namespaace对应dao类的全路径名   id对应dao的方法名
                String methodNamename = method.getName();
                String classNamee = method.getDeclaringClass().getName();
                String statementid= classNamee+"."+methodNamename;

                //根据返回值类型判断调用方法
                Type genericReturnType = method.getGenericReturnType();
                if (genericReturnType instanceof ParameterizedType){
                    List<Object> objects = selectList(statementid,args);
                    return objects;
                }


                return selectOne(statementid,args);
            }
        });
        return (T)proxyInstance ;
    }
}
