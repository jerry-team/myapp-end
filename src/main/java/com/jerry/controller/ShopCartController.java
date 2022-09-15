package com.jerry.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jerry.annotation.OperationLogAnnotation;
import com.jerry.common.vo.ShopCartVO;
import com.jerry.entity.*;
import com.jerry.service.ShopService;
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
 * @since 2022-07-04
 */
@RestController
@RequestMapping("/shop-cart")
public class ShopCartController extends BaseController {

//    @OperationLogAnnotation(operType = "加入购物车")
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

//    @PostMapping("/listByUserId")
//    public Result list(@RequestBody Map<String, Object> params){
//        User user = (User)request.getAttribute("User");
//        List<ShopCartVO> shopCartVOList = new ArrayList<>();
//        QueryWrapper<ShopCart> qw = new QueryWrapper<ShopCart>();
//        QueryWrapper<ShopCommodity> qws = new QueryWrapper<ShopCommodity>();
//        List<ShopCart> shopCartList = shopCartService.list(qw.eq("user_id",user.getId()));
//        for(ShopCart shopCart:shopCartList){
//            Commodity commodity = commodityService.getById(shopCart.getCommodityId());
//            System.out.println("commodity:" + commodity.getName());
//            ShopCartVO shopCartVO = new ShopCartVO(commodity);
//            ShopCommodity shopCommodity = shopCommodityService.getOne(qws.eq("commodity_id",shopCart.getCommodityId()));
//            System.out.println("ShopCommodity:" + shopCommodity.getShopId());
//            shopCartVO.setShop(shopService.getById(shopCommodity.getShopId()));
//            shopCartVOList.add(shopCartVO);
//        }
//
//        return Result.succ(shopCartVOList);
//    }

    @PostMapping("/listShop")
    public Result listShop(@RequestBody Map<String, Object> params){
        QueryWrapper<Commodity> qw = new QueryWrapper<Commodity>();
        List<Shop> shopList = shopService.getByShopCart();
        List<ShopCartVO> shopCartVOList = new ArrayList<>();
        for(Shop shop:shopList){
            ShopCartVO shopCartVO = new ShopCartVO(shop);
            shopCartVO.setChecked(false);
            shopCartVO.setCommodityList(commodityService.getByShopId(shop.getId()));
            shopCartVOList.add(shopCartVO);
        }

        return Result.succ(shopCartVOList);
    }

    @PostMapping("/delList")
    public String delList(@RequestBody Map<String, Object> params){
        QueryWrapper<ShopCart> qw = new QueryWrapper<ShopCart>();
//        for(Map.Entry val : params.entrySet())
//        {
//            System.out.println(val.getValue());
////            for(Commodity commodity : val.getValue())
////            {
////                System.out.println(commodity.getName());
////                shopCartService.remove(qw.eq("commodity_id",commodity.getId()));
////            }
//        }
//        System.out.printf((Commodity)params.get("goods"));
//        Commodity commodity = (Commodity)params.get("goods");
        System.out.println((Integer)params.get("commodity_id"));
        shopCartService.remove(qw.eq("commodity_id",(Integer)params.get("commodity_id")));
        return "删除成功！";
    }
}
