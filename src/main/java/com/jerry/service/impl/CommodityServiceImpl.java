package com.jerry.service.impl;

import com.jerry.entity.Commodity;
import com.jerry.mapper.CommodityMapper;
import com.jerry.service.CommodityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jerry
 * @since 2022-06-27
 */
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements CommodityService {
        @Autowired
        CommodityMapper commodityMapper;

        @Override
        public String getShopName(Integer cid){
            return commodityMapper.getShopName(cid);
        }

}
