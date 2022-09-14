package com.jerry.mapper;

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
    //查询总用户数量
    Integer getUserRow();
    //查询申请会员的用户数
    @Select("SELECT * From user WHERE state = 2")
    List<User> getUserRow2();
}
