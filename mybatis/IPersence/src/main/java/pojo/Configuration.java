package pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

    //数据源
    DataSource dataSource;
    //封装sql集合
    Map<String,MapperStatement> statement = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MapperStatement> getStatement() {
        return statement;
    }

    public void setStatement(Map<String, MapperStatement> statement) {
        this.statement = statement;
    }
}
