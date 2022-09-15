package com.jerry.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jerry.annotation.OperationLogAnnotation;
import com.jerry.entity.Commodity;
import com.jerry.entity.Result;
import com.jerry.entity.Search;
import com.jerry.entity.User;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchController extends BaseController {

    //展示历史搜索
    @PostMapping("/showSearchByTimeLimit")
    public Result showSearchByTimeLitmit(@RequestBody Map<String, Object> params) {
        QueryWrapper<User> userwrapper = new QueryWrapper<User>();
        User user = userService.getOne(userwrapper.eq("username", params.get("username")));
        Integer uid = user.getId();
        QueryWrapper<Search> searchQueryWrapper = new QueryWrapper<Search>();
        searchQueryWrapper
                .eq("uid", uid)
                .orderByDesc("datetime")
                .last("limit 5");
        List<Search> list_search = searchService.list(searchQueryWrapper);
        return Result.succ(list_search);
    }


    //展示常用搜索
    @PostMapping("/showSearchByNumLimit")
    public Result showSearchByNumLitmit(@RequestBody Map<String, Object> params) {
        QueryWrapper<User> userwrapper = new QueryWrapper<User>();
        User user = userService.getOne(userwrapper.eq("username", params.get("username")));
        Integer uid = user.getId();
        QueryWrapper<Search> searchQueryWrapper = new QueryWrapper<Search>();
        searchQueryWrapper
                .eq("uid", uid)
                .orderByDesc("snum")
                .last("limit 5");
        List<Search> list_search = searchService.list(searchQueryWrapper);
        return Result.succ(list_search);
    }


    //删除所有搜索记录
    @PostMapping("/deleteAllSearchById")
    public Result deleteAllSearchById(@RequestBody Map<String, Object> params) {
        try {
            jwtUtils.parseToken(params.get("token").toString());
        } catch (ExpiredJwtException e) {
            System.out.println("超时异常");
            return Result.fail("token超时,请尝试重新登录！");
        } catch (Exception e) {
            return Result.fail(e.toString());
        }
        QueryWrapper<User> userwrapper = new QueryWrapper<User>();
        User user = userService.getOne(userwrapper.eq("username", params.get("username")));
        Integer uid = user.getId();
        QueryWrapper<Search> searchQueryWrapper = new QueryWrapper<Search>();
        boolean judge = searchService.remove(searchQueryWrapper.eq("uid", uid));
        if (judge)
            return Result.succ(judge);
        else
            return Result.fail("删除失败，遇到未知错误！");
    }


    //进行搜索记录更新或插入的方法
    @PostMapping("/getSearchByVal")
    public Result getSearchByVal(@RequestBody Map<String, Object> params) {
        try {
            jwtUtils.parseToken(params.get("token").toString());
        } catch (ExpiredJwtException e) {
            System.out.println("超时异常");
            return Result.fail("token超时,请尝试重新登录！");
        } catch (Exception e) {
            return Result.fail(e.toString());
        }
        QueryWrapper<User> userwrapper = new QueryWrapper<User>();
        User user = userService.getOne(userwrapper.eq("username", params.get("username")));
        Integer uid = user.getId();
        QueryWrapper<Search> searchQueryWrapper = new QueryWrapper<Search>();
        Search search = new Search();
        search = searchService.getOne(searchQueryWrapper
                .eq("uid", uid)
                .eq("val", params.get("val")));

        if (search != null) { //搜索记录已经存在的情况
            search.setSnum(search.getSnum() + 1);
            QueryWrapper<Search> judge = new QueryWrapper<>();
            judge.eq("uid",uid)
                 .eq("val",params.get("val"));
            searchService.update(search,judge); //搜索次数+1
            return Result.succ(search);
        } else { //搜索记录不存在的情况
            search = new Search();
//            System.out.println(params.get("val"));
            search.setVal(params.get("val").toString());

            search.setSnum(1);
            search.setUid(uid);
            searchService.save(search);
            return Result.succ(search);
        }
    }

    //删除所有搜索记录
    @PostMapping("/deleteSearchByVal")
    public Result deleteSearchByVal(@RequestBody Map<String, Object> params) {
        QueryWrapper<User> userwrapper = new QueryWrapper<User>();
        User user = userService.getOne(userwrapper.eq("username", params.get("username")));
        Integer uid = user.getId();
        QueryWrapper<Search> searchQueryWrapper = new QueryWrapper<Search>();
        boolean judge = searchService.remove(searchQueryWrapper
                .eq("uid", uid)
                .eq("val", params.get("val")));
        if (judge)
            return Result.succ(judge);
        else
            return Result.fail("删除失败，遇到未知错误！");
    }

//    @OperationLogAnnotation(operType = "搜索")
    @PostMapping("/getCommodity")
    public Result getCommodity(@RequestBody Map<String, Object> params) {
        try {
            jwtUtils.parseToken(params.get("token").toString());
        } catch (ExpiredJwtException e) {
            System.out.println("超时异常");
            return Result.fail("token超时,请尝试重新登录！");
        } catch (Exception e) {
            return Result.fail(e.toString());
        }
        QueryWrapper<Commodity> commodityQueryWrapper = new QueryWrapper<Commodity>();
        commodityQueryWrapper
                .like("name",params.get("val"))
                .or()
                .like("category",params.get("val"))
                .or()
                .like("desription",params.get("val"));
        Commodity commodity = new Commodity();
        commodity = commodityService.getOne( commodityQueryWrapper);

        if(commodity != null)
            return Result.succ(commodity);
        else
            return Result.succ("没有找到相关商品");
    }

    @PostMapping("/pageQuery")
    public Result pageQuery(@RequestBody Map<String, Object> params)
    {
        try {
            jwtUtils.parseToken(params.get("token").toString());
        } catch (ExpiredJwtException e) {
            System.out.println("超时异常");
            return Result.fail("token超时,请尝试重新登录！");
        } catch (Exception e) {
            return Result.fail(e.toString());
        }
        Page<Commodity> page = new Page<>((Integer)params.get("start"), (Integer)params.get("pageSize"));
        QueryWrapper<Commodity> commodityQueryWrapper = new QueryWrapper<Commodity>();
        List<Commodity> commodity = new ArrayList<>();

        getSearchByVal(params);

        commodity = commodityMapper.selectPage(page, commodityQueryWrapper
                .like("name", params.get("val"))
                .or()
                .like("category", params.get("val"))
                .or()
                .like("description", params.get("val"))).getRecords();

        for(Commodity entity:commodity){
            entity.setShopName(commodityService.getShopName(entity.getId()));
        }
        if(commodity != null) {
            return Result.succ(commodity);
        }else
            return Result.succ("没有找到相关商品");

    }


}
