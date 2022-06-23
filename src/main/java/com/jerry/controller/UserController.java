package com.jerry.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jerry.common.vo.UserVO;
import com.jerry.entity.Result;
import com.jerry.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jerry
 * @since 2022-06-21
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, Object> params){
        QueryWrapper<User> userwrapper = new QueryWrapper<User>();
//        System.out.println(params);
        User user = userService.getOne(userwrapper.eq("username",params.get("username")));
        if(user == null || !user.getPassword().equals(params.get("password")))
        {
            return Result.fail("用户名或密码错误");
        }
        UserVO userVO = new UserVO(user);
        userVO.setToken(jwtUtils.generateToken(user.getUsername()));
        return Result.succ(userVO);
    }

    @GetMapping("/hello")
    public Date hello(){
        return new Date();
    }

    @PostMapping("/register")
    public Result register(User user){
        userService.save(user);
        return Result.succ(user);
    }
}
