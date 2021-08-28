package service;

import domain.PageBean;
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

    /**
     * 根据id删除对应数据
     * @param id
     */
    void deleteUser(String id);

    /**
     * 根据id查询对应user
     * @param id
     * @return
     */
    User findUserById(String id);

    /**
     * 更新指定user
     * @param user
     */
    void updateUser(User user);

    /**
     * 批量删除用户
     * @param ids
     */
    void delSelectedUsers(String[] ids);

    /**
     * 分页查询
     * @param currentPage
     * @param rows
     * @return
     */
    PageBean<User> findUserByPage(int currentPage, int rows);
}
