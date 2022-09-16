package com.jerry.controller;

import com.jerry.mapper.*;

import com.jerry.mapper.UserMapper;
import com.jerry.mapper.ShopMapper;
import com.jerry.service.*;

import com.jerry.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseController
{
    @Autowired
    UserService userService;

    @Autowired
    CommodityService commodityService;

    @Autowired
    ShopCartService shopCartService;

    @Autowired
    ShopService shopService;

    @Autowired
    CommodityPeripheryService commodityPeripheryService;

    @Autowired
    ShopCommodityService shopCommodityService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CommodityMapper commodityMapper;

    @Autowired
    CommodityPeripheryMapper commodityPeripheryMapper;

    @Autowired
    SearchService searchService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    OrdersService orderService;

    @Autowired
    CommentService commentService;

    @Autowired
    ShopMapper shopMapper;

    @Autowired
    HttpServletRequest request;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    BackendOrderMapper backendOrderMapper;
    @Autowired
    BackendOrderService backendOrderService;
    @Autowired
    UserMapper userMapper;

    @Autowired
    SysOperLogService sysOperLogService;

    @Autowired
    SysOperLogMapper sysOperLogMapper;
}
