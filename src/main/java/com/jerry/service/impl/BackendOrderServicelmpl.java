package com.jerry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jerry.entity.BackendOrder;

import com.jerry.mapper.BackendOrderMapper;

import com.jerry.service.BackendOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackendOrderServicelmpl extends ServiceImpl<BackendOrderMapper, BackendOrder> implements BackendOrderService {

    @Autowired BackendOrderMapper backendOrderMapper;


    @Override
    public List<BackendOrder> getAllOrder(){
        return backendOrderMapper.getAllOrder();
    }

    @Override
    public Integer deliveryCommodity(Integer oid,Integer status){
        return backendOrderMapper.deliveryCommodity(oid, status);
    }
}
