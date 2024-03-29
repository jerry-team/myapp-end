package com.jerry.service;

import com.jerry.entity.Shop;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jerry
 * @since 2022-06-29
 */
public interface ShopService extends IService<Shop> {

    List<Shop> getByShopCart();
    List<Shop> getAllShopName();
}
