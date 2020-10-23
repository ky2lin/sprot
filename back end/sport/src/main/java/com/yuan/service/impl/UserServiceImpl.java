package com.yuan.service.impl;

import com.yuan.dao.UserDao;
import com.yuan.pojo.User;
import com.yuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户业务层实现类
 */
@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    UserDao userDao;

    //根据用户名和密码获取信息
    @Override
    public User getUserByMessage(String username, String password) {
        return userDao.getUserByMessage(username,password);
    }

    //获取所有用户  pageStart:从哪里开始展示  pageSize：页面展示条数
    @Override
    public List<User> getAllUser(String username, int pageStart, int pageSize) {
        return userDao.getAllUser(username,pageStart,pageSize);
    }

    //获取所有用户的个数
    @Override
    public int getUserCounts(String username) {
        return userDao.getUserCounts(username);
    }

    //更新用户状态
    @Override
    public int updateState(Integer id, Boolean state) {
        return userDao.updateState(id,state);
    }

    //添加用户的方法
    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    //删除用户的方法
    @Override
    public int deleteUser(int id) {
        return userDao.deleteUser(id);
    }

    //修改用户的方法前，根据id查询出用户
    @Override
    public User getUpdateUser(int id) {
        return userDao.getUpdateUser(id);
    }

    //修改用户的方法前，根据查询出的用户进行修改
    @Override
    public int editUser(User user) {
        return userDao.editUser(user);
    }


}
