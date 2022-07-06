package com.jerry.mapper;

import com.jerry.entity.Commodity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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
    String getShopName(@Param("cid")Integer cid);

}
