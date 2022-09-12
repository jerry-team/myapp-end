package com.jerry.mapper;

import com.jerry.entity.Shop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jerry
 * @since 2022-06-29
 */
@Mapper
public interface ShopMapper extends BaseMapper<Shop> {

    @Select("select id,shop_name as name,shop_address,description from shop s " +
            "where s.id in " +
            "(select sc.shop_id from shop_cart sct,shop_commodity sc where sct.commodity_id = sc.commodity_id)")
    List<Shop> getByShopCart();

    List<Shop> getAllShopName();
}
