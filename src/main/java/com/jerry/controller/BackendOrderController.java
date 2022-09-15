package com.jerry.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jerry.entity.BackendOrder;
import com.jerry.entity.Commodity;
import com.jerry.entity.PageInfo;
import com.jerry.entity.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/backendorder")
public class BackendOrderController extends BaseController{

    @PostMapping("/getAllOrder")
    public Result getAllOrder(@RequestBody PageInfo req){
        Page page = new Page(req.get_currentPage(),req.getPageSize());
        List<BackendOrder> backendOrders = backendOrderMapper.selectPage(page,null).getRecords();
        return Result.succ(backendOrders);
    }

    @PostMapping("/getCount")
    public Result getCount(){
        return Result.succ(backendOrderService.count());
    }

    @PostMapping("/getChargeBack")
    public Result getChargeBack(){
//        Page page = new Page(req.get_currentPage(),req.getPageSize());
        QueryWrapper<BackendOrder> backendOrderQueryWrapper = new QueryWrapper<>();
        backendOrderQueryWrapper.eq("status",-2);
        List<BackendOrder> backendOrders = backendOrderMapper.selectList(backendOrderQueryWrapper);
        return Result.succ(backendOrders);
    }

    @PostMapping("/deliveryCommodity")
    public Result deliveryOrder(@RequestBody Map<String,Object> param){
        if(Integer.parseInt(param.get("status").toString())== 1){
            return Result.succ(backendOrderMapper.deliveryCommodity(Integer.parseInt(param.get("id").toString()),2));
        }else{
            return Result.fail("订单状态错误！请刷新页面重试");
        }
    }

    @PostMapping("/cancelOrder")
    public Result cancelOrder(@RequestBody Map<String,Object> param){
        Integer judge = Integer.parseInt(param.get("status").toString());
        if(judge == 0 || judge == 1){
            QueryWrapper<BackendOrder> bo = new QueryWrapper<>();
            BackendOrder backendOrder = backendOrderMapper.selectOne(bo.eq("id",param.get("id")));
            backendOrder.setStatus(-1);

//            commodityMapper.selectOne()
            return Result.succ(backendOrderMapper.update(backendOrder,bo));
        }else{
            return Result.fail("订单状态错误！请刷新页面重试");
        }
    }

    @PostMapping("/directCB")
    public Result directCB(@RequestBody Map<String,Object> param){
        Integer judge = Integer.parseInt(param.get("status").toString());
        if(judge == 3){
            QueryWrapper<BackendOrder> bo = new QueryWrapper<>();
            BackendOrder backendOrder = backendOrderMapper.selectOne(bo.eq("id",param.get("id")));
            backendOrder.setStatus(-4);
            backendOrder.setReason(param.get("reason").toString());
            return Result.succ(backendOrderMapper.update(backendOrder,bo));
        }
        return Result.fail("更新错误！状态码有误");
    }

    @PostMapping("/examineOrder")
    public Result examineOrder(@RequestBody Map<String,Object> param){
        Integer judge = Integer.parseInt(param.get("status").toString());
        if(judge == -2 ){
            QueryWrapper<BackendOrder> bo = new QueryWrapper<>();
            BackendOrder backendOrder = backendOrderMapper.selectOne(bo.eq("id",param.get("id")));
            backendOrder.setStatus(-3);
            return Result.succ(backendOrderMapper.update(backendOrder,bo));
        }
        return Result.fail("更新错误！状态码有误");
    }

    @PostMapping("/searchOrder")
    public Result searchOrder(@RequestBody Map<String,Object> params){
        String search = params.get("search").toString();
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(params.get("pageInfo")));
        Map<String, Object> pageInfoMap = json;
//        search = search.substring(0,search.length()-1);
        if(search != null) {
            QueryWrapper<BackendOrder> bo = new QueryWrapper<>();
            bo
                    .like("id", search).or()
                    .like("user_phone", search);
//                    .like("user_name", search).or()
//                    .like("user_address", search);

            Integer totalSearch = backendOrderService.count(bo);

            Page page = new Page(Integer.parseInt(pageInfoMap.get("_currentPage").toString()),
                    Integer.parseInt(pageInfoMap.get("pageSize").toString()));

            List<BackendOrder> backendOrders = backendOrderMapper.selectPage(page, bo).getRecords();
            if(totalSearch != 0){
                backendOrders.get(0).setSearchTotal(totalSearch);
            }
            return Result.succ(backendOrders);

        }else{
            Integer totalSearch = backendOrderService.count();
            Page page = new Page(Integer.parseInt(pageInfoMap.get("_currentPage").toString()),
                    Integer.parseInt(pageInfoMap.get("pageSize").toString()));
            List<BackendOrder> backendOrders = backendOrderMapper.selectPage(page,null).getRecords();
            if(totalSearch != 0){
                backendOrders.get(0).setSearchTotal(totalSearch);
            }
            return Result.succ(backendOrders);
        }
    }
}
