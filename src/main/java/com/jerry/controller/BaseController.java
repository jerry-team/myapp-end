package com.jerry.controller;

import com.jerry.mapper.CommodityMapper;
import com.jerry.mapper.CommodityPeripheryMapper;
import com.jerry.service.CategoryService;
import com.jerry.service.CommodityPeripheryService;
import com.jerry.service.CommodityService;
import com.jerry.service.UserService;

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
    CommodityPeripheryService commodityPeripheryService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CommodityMapper commodityMapper;

    @Autowired
    CommodityPeripheryMapper commodityPeripheryMapper;

    @Autowired
    JwtUtils jwtUtils;
}
