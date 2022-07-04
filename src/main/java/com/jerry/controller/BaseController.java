package com.jerry.controller;

import com.jerry.mapper.CommodityMapper;
import com.jerry.mapper.CommodityPeripheryMapper;
import com.jerry.mapper.SearchMapper;
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
    CommodityPeripheryService commodityPeripheryService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CommodityMapper commodityMapper;

    @Autowired
    CommodityPeripheryMapper commodityPeripheryMapper;

    @Autowired
    SearchService searchService;

    @Autowired
    JwtUtils jwtUtils;
}
