package com.jerry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jerry.entity.BackendOrder;

import java.util.List;

public interface BackendOrderService extends IService<BackendOrder> {
    List<BackendOrder> getAllOrder();
    Integer deliveryCommodity(Integer oid,Integer status);
}
