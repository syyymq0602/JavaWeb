package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dao= new UserDaoImpl();
    @Override
    public List<User> findAll() {
        // 调用dao完成查询
        return dao.findAll();
    }

    @Override
    public User login(User loginUser) {
        return dao.findUserByUsernameAndPassword(loginUser.getUsername(),loginUser.getPassword());
    }
}
