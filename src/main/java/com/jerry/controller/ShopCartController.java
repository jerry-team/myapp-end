package com.jerry.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jerry.entity.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jerry
 * @since 2022-07-04
 */
@RestController
@RequestMapping("/shop-cart")
public class ShopCartController extends BaseController {

    @PostMapping("/insertByCommodityId")
    public Result insert(@RequestBody Map<String, Object> params){
        User user = (User)request.getAttribute("User");
        QueryWrapper<ShopCart> qw = new QueryWrapper<ShopCart>();
        ShopCart shopCart = shopCartService.getOne(qw.and(i -> i.eq("user_id",user.getId()))
                                                            .and(i -> i.eq("commodity_id",(Integer)params.get("commodityId"))));

        if(shopCart != null){
            return Result.fail("购物车已有该商品！");
        }
        else
        {
            shopCart = new ShopCart();
            shopCart.setUserId(user.getId());
            shopCart.setCommodityId((Integer)params.get("commodityId"));
            shopCart.setNum(1);
            shopCartService.save(shopCart);
            return Result.succ(shopCart);
        }
    }

    @PostMapping("/getByCommodityId")
    public Result getByCommodityId(@RequestBody Map<String, Object> params){

        return Result.succ("");
    }
}
