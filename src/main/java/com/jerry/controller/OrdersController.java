package com.jerry.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jerry.entity.*;
import com.jerry.myapp.entity.OrderItemDTO;
import com.jerry.myapp.entity.OrdersDTO;
import org.aspectj.weaver.ast.Or;
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
 * @since 2022-09-06
 */
@RestController
@RequestMapping("/orders")
public class OrdersController extends BaseController {

    @PostMapping("/list")
    public Result list(@RequestBody Map<String, Object> params){
        User user = (User)request.getAttribute("User");
        List<Orders> orderList = new ArrayList<>();
        if((Integer)params.get("type") == 10){
            QueryWrapper<Orders> orderQueryWrapper = new QueryWrapper<>();
            orderList = orderService.list(orderQueryWrapper.eq("user_id",user.getId()));

        }else {
            QueryWrapper<Orders> orderQueryWrapper = new QueryWrapper<>();
            orderList = orderService.list(orderQueryWrapper.eq("user_id",user.getId())
                    .eq("status",(Integer)params.get("type")));
        }
        for(Orders order:orderList){
            QueryWrapper<OrderItem> orderItemQueryWrapper = new QueryWrapper<>();
            order.setOrderItemList(orderItemService.list(orderItemQueryWrapper.eq("order_id",order.getId())));
        }

        return Result.succ(orderList);
    }

    @PostMapping("/addOrder")
    public Result addOrder(@RequestBody Map<String, Object> params){
        User user = (User)request.getAttribute("User");
        QueryWrapper<Orders> oqw = new QueryWrapper<>();
        QueryWrapper<OrderItem> oiqw = new QueryWrapper<>();
        Gson gson = new Gson();
        String str = (String)params.get("OrdersList");
        List<OrdersDTO> ordersDTOList = gson.fromJson(str, new TypeToken<List<OrdersDTO>>() {}.getType());

        System.out.println(ordersDTOList);

        List<Orders> ordersList = new ArrayList<>();
        Double total_amount = 0.0;
        int allNum = orderService.count();//作为orderId
        for(OrdersDTO ordersDTO:ordersDTOList){
            allNum += 1;
            Orders orders = new Orders();
            List<OrderItemDTO> orderItemDTOList = ordersDTO.getOrderItemDTOList();
            List<OrderItem> orderItemList = new ArrayList<>();
            //        查询库存
            for(OrderItemDTO orderItemDTO:orderItemDTOList){
//                System.out.println(orderItemDTO);
                QueryWrapper<Commodity> cqw = new QueryWrapper<>();
                Commodity commodity = commodityService.getOne(cqw.eq("id",orderItemDTO.getCommodityId()));
//                System.out.println(commodity);
                if(commodity.getNumber() < orderItemDTO.getNum()){
                    return Result.fail(601,"库存不足",null);
                }
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderId(allNum);
                orderItem.setCommodityId(orderItemDTO.getCommodityId());
                orderItem.setNum(orderItemDTO.getNum());
                orderItem.setTotalAmount(orderItemDTO.getTotalAmount());
                orderItemList.add(orderItem);
            }
            orders.setId(allNum);
            orders.setUserId(user.getId());
            orders.setShopId(ordersDTO.getShopId());
            orders.setTotalAmount(ordersDTO.getTotalAmount());
            orders.setOrderItemList(orderItemList);
            orders.setUserName(user.getUsername());
            orders.setUserPhone(user.getTelephone());
            orders.setUserAddress("");
            orders.setStatus(0);
            ordersList.add(orders);
            total_amount += ordersDTO.getTotalAmount();

        }
        //        查询余额
        if(user.getMoney() < total_amount){
            return Result.fail(602,"金额不足",null);
        }

        //        加入订单表
        System.out.println(ordersList);
        for(Orders orders:ordersList){
            List<OrderItem> orderItemList1 = orders.getOrderItemList();
            for(OrderItem orderItem:orderItemList1){
                Commodity commodity = commodityService.getById(orderItem.getCommodityId());
                //减库存
                commodity.setNumber(commodity.getNumber()-1);
                commodityService.updateById(commodity);
                orderItemService.save(orderItem);
            }
//            orderItemService.saveBatch(orderItemList1);
            orderService.save(orders);
        }
        return Result.succ("已下单");
    }

    @PostMapping("/cancelOrder")
    public Result cancel(@RequestBody Map<String, Object> params){
        QueryWrapper<Orders> oqw = new QueryWrapper<>();
        Orders orders = orderService.getById((Integer)params.get("orderId"));
        orders.setStatus(-1);
        orders.setCloseType((Integer)params.get("flag"));
        orderService.updateById(orders);
        //加库存
        QueryWrapper<OrderItem> oiqw = new QueryWrapper<>();
//        orderItemService.list(oiqw.eq("order_id"));
        return Result.succ("");
    }

    @PostMapping("/payOrder")
    public Result pay(@RequestBody Map<String, Object> params){
        User user = (User)request.getAttribute("User");
        QueryWrapper<User> uqw = new QueryWrapper<>();
        Orders orders = orderService.getById((Integer)params.get("orderId"));
        if(orders.getTotalAmount() > user.getMoney()){
            return Result.fail(602,"金额不足",null);
        }
        else{
            user.setMoney(user.getMoney() - orders.getTotalAmount());
            userService.updateById(user);
            orders.setStatus(1);
            orderService.updateById(orders);
        }
        return Result.succ(200,"支付成功",null);
    }

    @PostMapping("/evaluateOrder")
    public Result evaluate(@RequestBody Map<String, Object> params){

        return Result.succ("");
    }

    @PostMapping("/receiveOrder")
    public Result receive(@RequestBody Map<String, Object> params){
        Orders orders = orderService.getById((Integer)params.get("orderId"));
        orders.setStatus(3);
        orderService.updateById(orders);
        return Result.succ(200,"确认收货!",null);
    }
}