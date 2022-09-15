package com.jerry.service.impl;

import com.jerry.entity.Orders;
import com.jerry.mapper.OrdersMapper;
import com.jerry.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jerry
 * @since 2022-09-06
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

}
