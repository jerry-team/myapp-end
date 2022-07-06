package com.jerry.mapper;

import com.jerry.entity.Commodity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jerry.entity.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jerry
 * @since 2022-06-27
 */
@Mapper
public interface CommodityMapper extends BaseMapper<Commodity> {

    @Select("select * from commodity c " +
            "where c.id in " +
            "(select commodity_id from shop_commodity where shop_id = #{shopId}) " +
            "and c.id in " +
            "(select commodity_id from shop_cart)")
    List<Commodity> getByShopId(Integer shopId);
}
