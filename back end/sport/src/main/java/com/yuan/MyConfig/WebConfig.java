package com.yuan.MyConfig;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //配置跨域
        registry.addMapping("/**")//添加允许跨域的路径
                .allowedOrigins("Http://localhost:8080")  //前端哪些域名被允许跨域,也就是请求来源
                .allowedMethods("GET","POST","PUT","OPTIONS","DELETE","HEAD","TRACE","CONNECT") //允许哪些请求方法跨域
                .allowCredentials(true) //是否允许携带信息,例如token
                .maxAge(3600);  //设置最大响应时间
    }
}
