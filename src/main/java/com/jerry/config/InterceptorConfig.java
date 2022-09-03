package com.jerry.config;

import com.jerry.interceptor.AuthInterceptor;
import com.jerry.service.UserService;
import com.jerry.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport
{
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(jwtUtils,userService))
                //拦截的url
                .addPathPatterns("/**")
                //排除的url
                .excludePathPatterns(excludePattern());
    }

    //解决  No mapping for GET /favicon.ico 访问静态资源图标
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/META-INF/resources/");
    }

    public List<String> excludePattern(){
        List<String> ret = new ArrayList<String>();
        ret.add("/backend/login");
        ret.add("/user/login");
        ret.add("/categoryIcon/**");
        ret.add("/commodityImages/**");
//        ret.add("/**");
//        ret.add("/book/*");
//        ret.add("/book/*/*");
//        ret.add("/category/*");
        return ret;
    }
}
