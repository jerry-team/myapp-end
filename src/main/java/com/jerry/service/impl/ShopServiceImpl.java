package com.jerry.service.impl;

import com.jerry.entity.Shop;
import com.jerry.mapper.ShopMapper;
import com.jerry.service.ShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jerry
 * @since 2022-06-29
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

    @Autowired
    private ShopMapper shopMapper;
    @Override
    public List<Shop> getByShopCart() {
        return shopMapper.getByShopCart();
    }
}
