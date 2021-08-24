package example.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 使用JDBC工具类的Druid连接池
 */
public class JDBCUtils {

    private static DataSource ds;

    static {
        try {
            Properties pro = new Properties();
            InputStream stream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            pro.load(stream);
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(){
        return ds;
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
