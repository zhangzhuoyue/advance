package utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.javassist.bytecode.SyntheticAttribute;

/**
 * @author zzy
 * @date 2020/7/2 21:53
 */
//案例模式，饿汉式
public class DruidUtils {

    private DruidUtils() {
    }

    private static DruidDataSource druidDataSource = new DruidDataSource();

    static {
        druidDataSource.setDriverClassName("");
        druidDataSource.setUrl("");
        druidDataSource.setUsername("");
        druidDataSource.setPassword("");
    }

    public static DruidDataSource getInstance(){
        return druidDataSource;
    }
}
