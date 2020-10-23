package com.yuan.controller;

import com.alibaba.fastjson.JSON;
import com.yuan.pojo.QueryInfo;
import com.yuan.pojo.User;
import com.yuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 用户控制器
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    //获取查询用户和进行分页操作
    @RequestMapping("/allUser")
    public String getUserList(QueryInfo queryInfo) {
        //查询用户总数，模糊查询用户的总数,
        int counts = userService.getUserCounts("%" + queryInfo.getUsername() + "%");
        //分页公式(当前页数-1)*每页最大数
        int pageStart = (queryInfo.getPageNum() - 1) * queryInfo.getPageSize();

        //获取所有的用户,并进行分页操作
        List<User> users = userService.getAllUser("%" + queryInfo.getUsername() + "%", pageStart, queryInfo.getPageSize());
        //
        HashMap<String, Object> res = new HashMap<>();
        res.put("counts", counts); //把总条数存入map
        res.put("users", users); //把所有用户存入map
        String res_string = JSON.toJSONString(res); //转换为json字符串传给前端
        return res_string;
    }

    //更新用户状态
    @RequestMapping("/userState")
    public String updateUserState(@RequestParam("id") Integer id, @RequestParam("state") Boolean state) {

        int i = userService.updateState(id, state);
        return i > 0 ? "success" : "error";
    }

    //添加用户的方法
    @RequestMapping("/addUser")
    public String addUser(@RequestBody User user){
        user.setRole("普通用户"); //初始化状态
        user.setState(false);//初始化状态
        int i = userService.addUser(user);
        return i > 0 ? "success" : "error";
    }

    //删除用户的方法
    @RequestMapping("/deleteUser")
    public String deleteUser(int id){
        int i =userService.deleteUser(id);
        return i > 0 ? "success" : "error";
    }

    //更新前获取用户的ID信息方法
    @RequestMapping("/getUpdate")
    public String getUpdateUser(int id){
        User user = userService.getUpdateUser(id); //根据ID查询到用户
        String s = JSON.toJSONString(user);
        return s;
    }

    //更新前根据查询到的用户进行修改
    @RequestMapping("/editUser")
    public String editUser(@RequestBody User user){
        int i = userService.editUser(user);
        return i > 0 ? "success" : "error";
    }
}
