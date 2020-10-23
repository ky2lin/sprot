package com.yuan.service;

import com.yuan.pojo.MainMenu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单业务层实现类
 */

public interface MenuService {
    //获取所有的菜单，因为主菜单中包含了子菜单
    List<MainMenu> getMenus();
}
