package com.jerry.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jerry.entity.Result;
import com.jerry.entity.Shop;
import com.jerry.entity.ShopCart;
import com.jerry.entity.ShopCommodity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jerry
 * @since 2022-06-29
 */
@RestController
@RequestMapping("/shop")
public class ShopController extends BaseController {

    @PostMapping("/getByCommodityId")
    public Result getByCommodityId(@RequestBody Map<String, Object> params){
        QueryWrapper<ShopCommodity> qw = new QueryWrapper<ShopCommodity>();
        ShopCommodity shopCommodity = shopCommodityService.getOne(qw.eq("commodity_id",(Integer)params.get("commodityId")));

        QueryWrapper<Shop> qws = new QueryWrapper<Shop>();
        Shop shop = shopService.getOne(qws.eq("id",shopCommodity.getShopId()));
        return Result.succ(shop);
    }

    @PostMapping("/getAllShopName")
    public Result getAllShopName(){

        List<Shop> shopArrayList = shopMapper.getAllShopName();

        return Result.succ(shopArrayList);
    }
}
