package com.jerry.mapper;

import com.jerry.entity.Commodity;
import com.jerry.entity.User;
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
 * @since 2022-06-21
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

//    @Select("select * from commodity c " +
//            "where c.id in " +
//            "(select commodity_id from shop_commodity where shop_id = #{shopId}) " +
//            "and c.id in " +
//            "(select commodity_id from shop_cart)")
//    List<Commodity> getByShopId(Integer shopId);
}
