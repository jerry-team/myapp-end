package com.jerry.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jerry.common.vo.UserVO;
import com.jerry.entity.*;
import com.jerry.utils.PageUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
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
            user1.setMoney(100000.00);
            userService.save(user1);
            return Result.succ(user1);
        }else{
            return Result.fail("用户名已存在！");
        }

    }

    @PostMapping("/nickname")
    public Result getNickname(@RequestBody Map<String, Object> params){
        QueryWrapper<User> userwrapper = new QueryWrapper<User>();
        User user = (User)request.getAttribute("User");
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

    @PostMapping("/login2")
    public Result login2(@RequestBody Map<String, Object> params){
        QueryWrapper<User> userwrapper = new QueryWrapper<User>();
        User user = userService.getOne(userwrapper.eq("username",params.get("username")));
        if(user.getState() != -1){
            return Result.fail("该用户不是管理员");
        }
        if(user == null || !user.getPassword().equals(params.get("password")))
        {
            return Result.fail("用户名或密码错误");
        }
        UserVO userVO = new UserVO(user);
        userVO.setToken(jwtUtils.generateToken(user.getUsername(),user.getState()));
        return Result.succ(userVO);
    }
    @PostMapping("/UserGetRow")
    public Result getRow(){

        return Result.succ(userMapper.getUserRow());
    }
    @PostMapping("/UserPageQuery")
    public Result getInfo(@RequestBody PageInfo req) {
        {
            Page<User> page = new Page<>(req.get_currentPage(), req.getPageSize());

            List<User> usersList = userMapper.selectPage(page, null).getRecords();
            //System.out.println(usersList);
            return Result.succ(usersList);
        }
    }
    @DeleteMapping("/UserDelete/{id}")
    public Result deleteUser(@PathVariable("id") Integer id){
        QueryWrapper<User> ur = new QueryWrapper<>();
        ur.eq("id",id);
        if(userMapper.delete(ur) == 1)
            return Result.succ("删除成功");
        else
            return Result.fail("删除失败，并发错误");
    }
    @PostMapping("/update")
    public Result updateUser(@RequestBody Map<String, Object> params){
        System.out.println(params);
        User user1 = new User();
        user1.setUsername(params.get("username").toString());
        user1.setTelephone(params.get("telephone").toString());
        user1.setNickname(params.get("nickname").toString());
        user1.setEmail(params.get("email").toString());
        if(params.get("state").equals("1"))
        {
            user1.setState(1);
        }else if(params.get("state").equals("0")){
            user1.setState(0);
        }else if(params.get("state").equals("-1"))
        {
            user1.setState(-1);
        }else{
            user1.setState(2);
        }
        UpdateWrapper<User> Wrapper = new UpdateWrapper<>();
        Wrapper.eq("id",(Integer)params.get("id"));
        if(Wrapper != null){
            userMapper.update(user1,Wrapper);
        }
        QueryWrapper<User> QW = new QueryWrapper<>();
        QW.eq("username",user1.getUsername())
                .eq("telephone",user1.getTelephone())
                .eq("nickname",user1.getNickname())
                .eq("email",user1.getEmail())
                .eq("state",user1.getState());
        if (QW != null){
            return Result.succ("修改成功");
        }
        else
        return  Result.fail("修改失败");
    }
    @PostMapping("/update2")
    public Result applyMember(@RequestBody Map<String, Object> params){
        User user = (User)request.getAttribute("User");
        if(user.getState() == 1 || user.getState() == -1){
            return Result.succ(user);
        }
        if((Integer)params.get("state") == 2 && user.getState() == 0){
            user.setState(2);
            System.out.println(user.getState());
        }
        UpdateWrapper<User> Wrapper = new UpdateWrapper<>();
        Wrapper.eq("username",user.getUsername())
                .eq("telephone",user.getTelephone());
        if(Wrapper != null){
            userMapper.update(user,Wrapper);
        }
        QueryWrapper<User> QW = new QueryWrapper<>();
        QW.eq("username",user.getUsername())
                .eq("telephone",user.getTelephone())
                .eq("nickname",user.getNickname())
                .eq("email",user.getEmail())
                .eq("state",user.getState());
        if (QW != null){
            return Result.succ(user);
        }
        else
            return  Result.fail("ur");
    }
    @PostMapping("/ApplyGetRow")
    public Result getRow2(){
        List<User> user = userMapper.getUserRow2();
        return Result.succ(user.size());
    }
    @PostMapping("/getApply")
    public Result getApply(@RequestBody PageInfo req){
        QueryWrapper<User> ur = new QueryWrapper<>();
        //查询申请成为会员的用户
        ur.eq("state",2);
        Page<User> page = new Page<>(req.get_currentPage(), req.getPageSize());
        List<User> usersList = userMapper.selectPage(page, ur).getRecords();
        return Result.succ(usersList);
    }
}
