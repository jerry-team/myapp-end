package com.jerry.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jerry.entity.User;
import com.jerry.service.UserService;
import com.jerry.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor
{

    private JwtUtils jwtUtils;
    private UserService userService;

    public AuthInterceptor(JwtUtils jwtUtils, UserService userService){
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object o) throws Exception{
        String token = req.getHeader("token");
        if(token == null)
        {
            throw new Exception("请登录！");
        }
        Claims claim = jwtUtils.getClaimByToken(token);
        if(claim == null)
        {
            throw new JwtException("token异常！");
        }
        if(jwtUtils.isTokenExpired(claim))
        {
            throw new JwtException("token已过期！");
        }
        String username = claim.getSubject();
        QueryWrapper<User> qw = new QueryWrapper<User>();
        User user = userService.getOne(qw.eq("username",username));
        if(user == null)
        {
            throw new Exception("用户验证失败");
        }
        req.setAttribute("User", user);
        req.setAttribute("UserToken", token);
        return true;
    }
}
