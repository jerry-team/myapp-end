package com.jerry.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jerry.common.vo.UserVO;
import com.jerry.entity.Register;
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
    public Result register(@RequestBody Map<String, Object> params){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        User user = userService.getOne(userQueryWrapper.eq("username",params.get("rusername")));
        System.out.println(user);

        if(user == null){
            User user1 = new User();
            user1.setUsername(params.get("rusername").toString());
            user1.setNickname(params.get("rusername").toString());
            user1.setPassword(params.get("rpassword").toString());
            user1.setTelephone(params.get("rphone").toString());
            user1.setEmail(params.get("remail").toString());
            user1.setState(0);
            userService.save(user1);
            return Result.succ(user1);
        }else{
            return Result.fail("用户名已存在！");
        }

    }


}
