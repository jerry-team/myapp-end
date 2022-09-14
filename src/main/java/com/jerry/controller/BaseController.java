package com.jerry.controller;

import com.jerry.mapper.*;

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

    ShopMapper shopMapper;

    HttpServletRequest request;


    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    BackendOrderMapper backendOrderMapper;
    @Autowired
    BackendOrderService backendOrderService;
}
