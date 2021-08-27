package service;

import domain.User;

import java.util.List;

/**
 * 用户管理的业务接口
 */
public interface UserService {
    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();

    /**
     * 获取正确登录的用户
     * @param user
     * @return 成功登录的用户
     */
    public User login(User user);

    /**
     * 保存新的User
     * @param user
     */
    void addUser(User user);
}
