package com.yuan.controller;


import com.alibaba.fastjson.JSON;
import com.yuan.dao.UserDao;
import com.yuan.pojo.User;
import com.yuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 登录控制器
 */
@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String login(@RequestBody User user) {
        //设置初始信息
        String flag = "error";
        User us = userService.getUserByMessage(user.getUsername(), user.getPassword());
        HashMap<String, Object> res = new HashMap<>();
        if (us != null) { //us不等于空，说明有结果
            flag = "ok";
        }
        res.put("flag", flag);
        res.put("user", us);
        //res.put("user", user);
        //使用json
        String res_json = JSON.toJSONString(res); //把res结果集转换为json字符串
        return res_json;

    }
}
