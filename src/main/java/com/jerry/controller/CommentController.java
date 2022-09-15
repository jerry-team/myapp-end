package com.jerry.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jerry.entity.*;
import com.jerry.myapp.entity.OrdersDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jerry
 * @since 2022-09-01
 */
@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController {


    @PostMapping("/listByCommodityId")
    public Result list(@RequestBody Map<String, Object> params){
        return Result.succ(commentService.listByCommodityId((Integer) params.get("commodityId")));
    }

    @PostMapping("/add")
    public Result add(@RequestBody Map<String, Object> params){
        User user = (User)request.getAttribute("User");
        Comment comment = new Comment();
        comment.setCommodityId((Integer) params.get("commodityId"));
        comment.setContent(params.get("content").toString());
        comment.setParentId((Integer) params.get("parentId"));
        comment.setUserId(user.getId());
        commentService.save(comment);
        return Result.succ(comment);
    }

    @PostMapping("/test")
    public Result test(@RequestBody Map<String, Object> params){
//        User user = (User)request.getAttribute("User");
//        System.out.println(params);
//                Gson gson = new Gson();
//        String str = (String)params.get("test");
//        List<Orders> test = gson.fromJson(str, new TypeToken<List<Orders>>() {}.getType());
//        System.out.println(test);
        return Result.succ(506,"test",null);
    }

}
