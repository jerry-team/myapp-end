package com.jerry.config;

import com.jerry.interceptor.AuthInterceptor;
import com.jerry.service.UserService;
import com.jerry.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
    public void addInterceptors(InterceptorRegistry reg) {
        reg.addInterceptor(new AuthInterceptor(jwtUtils,userService))
                //拦截的url
                .addPathPatterns("/**")
                //排除的url
                .excludePathPatterns(excludePattern());
    }

    public List<String> excludePattern(){
        List<String> ret = new ArrayList<String>();
        ret.add("/user/login");
//        ret.add("/**");
//        ret.add("/book/*");
//        ret.add("/book/*/*");
//        ret.add("/category/*");
        return ret;
    }
}
