package com.jerry.service;

import com.jerry.entity.Commodity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jerry
 * @since 2022-06-27
 */

public interface CommodityService extends IService<Commodity> {

    List<Commodity> getByShopId(Integer shopId);
    String getShopName(Integer cid);

    List<Commodity> getRecommend();
    String getCategoryInfo(Integer categoryId);

    Integer getCommodityRow();

    List<Commodity> getCommodityByShopId(Integer sid);

    List<Commodity> getAllCommodity();

}
