package com.jerry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jerry.entity.BackendOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BackendOrderMapper extends BaseMapper<BackendOrder> {
    List<BackendOrder> getAllOrder();

    Integer deliveryCommodity(@Param("oid") Integer oid,@Param("status") Integer status);

}
