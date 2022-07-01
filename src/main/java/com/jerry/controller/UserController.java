package com.jerry.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jerry.common.vo.UserVO;
import com.jerry.entity.Register;
import com.jerry.entity.Result;
import com.jerry.entity.User;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
        User user = userService.getOne(userwrapper.eq("username",params.get("username")));
        if(user == null || !user.getPassword().equals(params.get("password")))
        {
            return Result.fail("用户名或密码错误");
        }
        UserVO userVO = new UserVO(user);
        userVO.setToken(jwtUtils.generateToken(user.getUsername(),user.getState()));
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

    @PostMapping("/nickname")
    public Result getNickname(@RequestBody Map<String, Object> params){
        QueryWrapper<User> userwrapper = new QueryWrapper<User>();
        try {
            jwtUtils.parseToken(params.get("token").toString());
        } catch (ExpiredJwtException e) {
            System.out.println("超时异常");
            return Result.fail("token超时,请尝试重新登录！");
        }catch(Exception e){
            return Result.fail(e.toString());
        }
        User user = userService.getOne(userwrapper.eq("username",params.get("username")));
        return Result.succ(user);
    }

    @PostMapping("/editnickname")
    public Result editNickname(@RequestBody Map<String, Object> params){
        QueryWrapper<User> userwrapper = new QueryWrapper<User>();
        try {
            jwtUtils.parseToken(params.get("token").toString());
        } catch (ExpiredJwtException e) {
            System.out.println("超时异常");
            return Result.fail("token超时,请尝试重新登录！");
        }catch(Exception e){
            return Result.fail(e.toString());
        }
        String regNickname = "^[0-9A-Za-z]{6,16}$";
        User user = userService.getOne(userwrapper.eq("username",params.get("username")));
        Pattern nickname_p;
        Matcher nickname_m;
        nickname_p = Pattern.compile(regNickname);
        nickname_m = nickname_p.matcher(params.get("edit_nickname").toString());
        if(nickname_m.matches()) {
            user.setNickname(params.get("edit_nickname").toString());
            userService.update(user, userwrapper.eq("username", params.get("username")));
            return Result.succ(user);
        } else{
            return Result.fail("修改失败！请输入6-14不含空格的字符");
        }
    }

    @PostMapping("/checkToken")
    public Result userCheck( @RequestBody Map<String, Object> params ) {
        try {
            String token = params.get("token").toString();
            jwtUtils.parseToken(token);
        } catch (ExpiredJwtException e) {
            System.out.println("超时异常");
            return Result.fail("token超时,请尝试重新登录！");
        }catch(Exception e){
            return Result.fail(e.toString());
        }
        return Result.succ("token有效！");
    }


    @PostMapping("/editemail")
    public Result editEmail(@RequestBody Map<String, Object> params){
        QueryWrapper<User> userwrapper = new QueryWrapper<User>();
        try {
            jwtUtils.parseToken(params.get("token").toString());
        } catch (ExpiredJwtException e) {
            System.out.println("超时异常");
            return Result.fail("token超时,请尝试重新登录！");
        }catch(Exception e){
            return Result.fail(e.toString());
        }
        String regEmail = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$";
        User user = userService.getOne(userwrapper.eq("username",params.get("username")));
        Pattern email_p;
        Matcher email_m;
        email_p = Pattern.compile(regEmail);
        email_m = email_p.matcher(params.get("edit_email").toString());
        if(email_m.matches()) {
            user.setEmail(params.get("edit_email").toString());
            userService.update(user, userwrapper.eq("username", params.get("username")));
            return Result.succ(user);
        } else{
            return Result.fail("修改失败！邮箱格式不正确！");
        }
    }

    @PostMapping("/editphone")
    public Result editPhone(@RequestBody Map<String, Object> params){
        QueryWrapper<User> userwrapper = new QueryWrapper<User>();
        try {
            jwtUtils.parseToken(params.get("token").toString());
        } catch (ExpiredJwtException e) {
            System.out.println("超时异常");
            return Result.fail("token超时,请尝试重新登录！");
        }catch(Exception e){
            return Result.fail(e.toString());
        }
        String regPhone = "^[0-9]{11,11}$";
        User user = userService.getOne(userwrapper.eq("username",params.get("username")));
        Pattern phone_p;
        Matcher phone_m;
        phone_p = Pattern.compile(regPhone);
        phone_m = phone_p.matcher(params.get("edit_phone").toString());
        if(phone_m.matches()) {
            user.setEmail(params.get("edit_phone").toString());
            userService.update(user, userwrapper.eq("username", params.get("username")));
            return Result.succ(user);
        } else{
            return Result.fail("修改失败！电话格式不正确！");
        }
    }

//    @PostMapping("/nickname")
//    public Result getNickname(@RequestBody Map<String, Object> params){
//        QueryWrapper<User> userwrapper = new QueryWrapper<User>();
//        try {
//            jwtUtils.parseToken(params.get("token").toString());
//        } catch (ExpiredJwtException e) {
//            System.out.println("超时异常");
//            return Result.fail("token超时,请尝试重新登录！");
//        }catch(Exception e){
//            return Result.fail(e.toString());
//        }
//        User user = userService.getOne(userwrapper.eq("username",params.get("username")));
//        return Result.succ(user);
//    }



}
