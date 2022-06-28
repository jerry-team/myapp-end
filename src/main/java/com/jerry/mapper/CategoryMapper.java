package com.jerry.mapper;

import com.jerry.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jerry
 * @since 2022-06-27
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
