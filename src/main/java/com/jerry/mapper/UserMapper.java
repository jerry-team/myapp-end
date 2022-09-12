package com.jerry.mapper;

import com.jerry.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

    Integer getUserRow();
}
