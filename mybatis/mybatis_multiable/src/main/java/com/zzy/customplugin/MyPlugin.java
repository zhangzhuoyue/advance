package com.zzy.customplugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author zzy
 * @date 2020/6/21 10:58
 */
@Intercepts({//声明一个拦截器，注意大括号，里面可以声明多个@Signature对多个地方进行拦截，都用这个拦截器
        @Signature(type = StatementHandler.class,//指定拦截哪个接口，必须是mybatis四个组件之一
        method = "prepare",   //对接口中哪个方法进行拦截
        args = {Connection.class,Integer.class})//拦截方法的入参，按照顺序写，，如果方法重载，可以通过方法名和入参唯一确定。
})
public class MyPlugin implements Interceptor {

    private final Logger logger = LoggerFactory.getLogger(MyPlugin.class);

    //每次执行都会进入到这个拦截器方法内
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //增强逻辑
        System.out.println("intercept");

        return invocation.proceed();//执行原方法
    }

    //主要讲拦截器生成代理，放到拦截器链中
    @Override
    public Object plugin(Object target) {
        System.out.println("将要包装的对象" + target);
        return Plugin.wrap(target, this);
    }

    //获取配置文件属性
    /*
     * 初始化插件调用，只调用一次，插件配置的属性从这里设置进来
     * */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件配置初始化参数" + properties);
    }
}
