package com.yuan.service;

import com.yuan.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户业务层实现接口
 */

public interface UserService {
    //根据用户名和密码获取信息
    User getUserByMessage(@Param("username") String username, @Param("password") String password);

    //获取所有用户  pageStart:从哪里开始展示  pageSize：页面展示条数
    List<User> getAllUser(@Param("username") String username, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    //获取所有用户的个数
    int getUserCounts(@Param("username") String username);

    //更新用户状态
    int updateState(Integer id,Boolean state);

    //添加用户的方法
    int addUser(User user);

    //删除用户的方法
    int deleteUser(int id);

    //修改用户的方法
    User getUpdateUser(int id);//根据id查询出用户
    int editUser(User user);//根据查询出的用户进行修改
}
