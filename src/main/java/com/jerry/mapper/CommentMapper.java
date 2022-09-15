package com.jerry.mapper;

import com.jerry.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jerry
 * @since 2022-09-01
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
