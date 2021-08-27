package dao;

import domain.User;

import java.util.List;

/**
 * 用户操作的Dao
 */
public interface UserDao {
    public List<User> findAll();

    public User findUserByUsernameAndPassword(String username,String password);

    void addUser(User user);

    /**
     * 数据库删除操作
     * @param id
     */
    void delete(int id);

    /**
     * 根据id查找对应user
     * @param id
     * @return
     */
    User findById(int id);

    /**
     * 更新用户信息
     * @param user
     */
    void update(User user);
}
