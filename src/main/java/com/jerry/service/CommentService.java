package com.jerry.service;

import com.jerry.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jerry
 * @since 2022-09-01
 */
public interface CommentService extends IService<Comment> {

    public List<Comment> listByCommodityId(Integer commodityId);

}
