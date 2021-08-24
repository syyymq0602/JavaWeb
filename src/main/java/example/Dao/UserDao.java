package example.Dao;

import example.domain.User;
import example.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 操作MYSQL数据库中USER表的类
 */
public class UserDao {
    // 共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 登陆方法
     * @param loginUser 只有用户名和密码
     * @return 包含用户全部数据
     */
    public User login(User loginUser){
        try{
            String sql = "select * from USER where username = ? and password = ?";
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(),
                    loginUser.getPassword());
            return user;
        }catch (DataAccessException e){
            System.out.println("------------------------");
            e.printStackTrace();
            return null;
        }
    }
}
