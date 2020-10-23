package com.yuan.controller;

import com.alibaba.fastjson.JSON;
import com.yuan.pojo.MainMenu;
import com.yuan.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 菜单控制器
 */
@RestController
public class MenuController {
    @Autowired
    MenuService menuService;

    @RequestMapping("/menus")
    public String getAllMenus(){
        System.out.println("访问成功");
        HashMap<String, Object> data = new HashMap<>();
        List<MainMenu> menus = menuService.getMenus();
        if (menus!=null){
            data.put("menus",menus);
            data.put("flag",200);//成功是200 ，错误404
        }else {
            data.put("flag",404);//成功是200 ，错误404
        }
        String s = JSON.toJSONString(data); //转换为json字符串
        return s;
    }
}
