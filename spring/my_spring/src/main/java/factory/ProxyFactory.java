package factory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zzy
 * @date 2020/7/4 15:54
 */
public class ProxyFactory {

private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    //jdk动态代理
    public Object getJDKProxyObject(Object object) {
        Object obj = Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = null;

                try {
                    //开始失去（关闭事务自动提交）
                    transactionManager.beginTransaction();
                    result = method.invoke(object, args);
                    transactionManager.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    transactionManager.rollback();
                }
                return result;
            }
        });
        return obj;
    }

    //cglib动态代理
    public Object getCglibProxyBean(Object object){
        return Enhancer.create(object.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object result = null;
                try {
                    //开启事务（关闭自动提交）
                    transactionManager.beginTransaction();
                    result = method.invoke(object,objects);
                    transactionManager.commit();
                }catch (Exception e){
                    transactionManager.rollback();
                }
                return result;
            }
        });
    }
}
