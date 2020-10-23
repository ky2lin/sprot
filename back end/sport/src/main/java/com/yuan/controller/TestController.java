package com.yuan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 */
@RestController
public class TestController {


    @RequestMapping("/test")
    public String test(){
        return "ok";
    }

}
