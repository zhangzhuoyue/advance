package com.zzy.annotationDao.dynamicSQL;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.zzy.pojo.User;
import org.apache.ibatis.annotations.Case;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;

import java.util.List;

/**
 * @author zzy
 * @date 2020/6/20 10:51
 * mybatis3.5.1 后可以使用ProviderMethodResolver接口
 * // 在你的 provider 类中实现 ProviderMethodResolver 接口，默认实现中，会将映射器方法的调用解析到实现的同名方法上
 */
public class UserProvider implements ProviderMethodResolver {

    public static String findUserByDynamic(final User user) {

        return new SQL() {
            {
                SELECT("id ,username,password,birthady");
                FROM("`user`");
                if (user.getPassword() != null) {
                    WHERE("username = #{username}");
                }
                if (user.getPassword() != null) {
                    WHERE("password=#{password}");
                }
            }
        }.toString();
    }


}
