package com.jerry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jerry.entity.UserAddress;
import com.jerry.mapper.UserAddressMapper;
import com.jerry.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {
    @Autowired
    UserAddressMapper userAddressMapper;
}
