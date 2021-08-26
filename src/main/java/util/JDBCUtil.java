package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 使用JDBC工具类的Druid连接池
 */
public class JDBCUtil {

    private static DataSource ds;

    static {
        try {
            Properties pro = new Properties();
            InputStream stream = JDBCUtil.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(stream);
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接池方法
     * @return 连接池
     */
    public static DataSource getDataSource(){
        return ds;
    }

    /**
     * 获取连接Connection对象
     * @return 数据据连接
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
