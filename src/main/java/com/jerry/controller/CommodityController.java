package com.jerry.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jerry.common.vo.UserVO;
import com.jerry.entity.Commodity;
import com.jerry.entity.Result;
import com.jerry.entity.User;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jerry
 * @since 2022-06-27
 */
@RestController
@RequestMapping("/commodity")
public class CommodityController extends BaseController {

    @PostMapping("/list")
    public Result list(){
        return Result.succ(commodityService.list());
    }

    @PostMapping("/listByCategoryId")
    public Result list(@RequestBody Map<String, Object> params){
        QueryWrapper<Commodity> qw = new QueryWrapper<Commodity>();
        List<Commodity> commodityList = commodityService.list(qw.eq("category",params.get("categoryId")));
        return Result.succ(commodityList);
    }

    @PostMapping("/listById")
    public Result listById(@RequestBody Map<String, Object> params){
        QueryWrapper<Commodity> qw = new QueryWrapper<Commodity>();
        Commodity commodity = commodityService.getById((Serializable) params.get("commodityId"));
//        Commodity commodity = commodityService.list(qw.eq("id",params.get("commodityId")));
        return Result.succ(commodity);
    }

    @PostMapping("/pageQuery")
    public Result pageQuery(@RequestBody Map<String, Object> params)
    {

//        User user = (User)request.getAttribute("User");
//        System.out.println("userTest:" + user.getPassword());
        Page<Commodity> page = new Page<>((Integer)params.get("start"), (Integer)params.get("pageSize"));
        if((Integer)params.get("categoryId") <= 1)
            return Result.succ(commodityMapper.selectPage(page,null).getRecords());
        else
        {
            QueryWrapper<Commodity> wrapper = new QueryWrapper<Commodity>();
            return Result.succ(commodityMapper.selectPage(page,wrapper.eq("category",(Integer)params.get("categoryId")-1)).getRecords());
        }
    }
//
//    @PostMapping("/list")
//    public Result list(@RequestBody Map<String, Object> params){
//
//        return Result.succ("");
//    }

}
