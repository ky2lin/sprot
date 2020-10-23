package com.yuan.pojo;

import java.util.List;

/**
 * 主菜单实体类
 */
public class MainMenu {
    private int id;
    private String title; //标题
    private String path; //路径
    private List<SubMenu> sList; //子菜单

    public MainMenu() {
    }

    public MainMenu(String title, String path, List<SubMenu> sList) {
        this.title = title;
        this.path = path;
        this.sList = sList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<SubMenu> getsList() {
        return sList;
    }

    public void setsList(List<SubMenu> sList) {
        this.sList = sList;
    }

    @Override
    public String toString() {
        return "MainMenu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", sList=" + sList +
                '}';
    }
}
