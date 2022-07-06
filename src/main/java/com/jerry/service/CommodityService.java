package com.jerry.service;

import com.jerry.entity.Commodity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jerry
 * @since 2022-06-27
 */
public interface CommodityService extends IService<Commodity>{
        String getShopName(Integer cid);
}
