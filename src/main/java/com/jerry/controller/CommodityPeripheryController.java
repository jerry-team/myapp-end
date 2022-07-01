package com.jerry.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jerry.entity.Commodity;
import com.jerry.entity.CommodityPeriphery;
import com.jerry.entity.Result;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/commodity-periphery")
public class CommodityPeripheryController extends BaseController {
    @PostMapping("/list")
    public Result list(){
        return Result.succ(commodityPeripheryService.list());
    }

    @PostMapping("/listByCategoryId")
    public Result list(@RequestBody Map<String, Object> params){
        QueryWrapper<CommodityPeriphery> qw = new QueryWrapper<CommodityPeriphery>();
        List<CommodityPeriphery> commodityList = commodityPeripheryService.list(qw.eq("category",params.get("categoryId")));
        return Result.succ(commodityList);
    }

    @PostMapping("/pageQuery")
    public Result pageQuery(@RequestBody Map<String, Object> params)
    {
        Page<CommodityPeriphery> page = new Page<>((Integer)params.get("start"), (Integer)params.get("pageSize"));
        if((Integer)params.get("categoryId") == 0)
            return Result.succ(commodityPeripheryMapper.selectPage(page,null).getRecords());
        else
        {
            QueryWrapper<CommodityPeriphery> wrapper = new QueryWrapper<CommodityPeriphery>();
            return Result.succ(commodityPeripheryMapper.selectPage(page,wrapper.eq("category",(Integer)params.get("categoryId"))).getRecords());
        }
    }

}
