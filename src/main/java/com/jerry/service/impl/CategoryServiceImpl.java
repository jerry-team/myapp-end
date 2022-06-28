package com.jerry.service.impl;

import com.jerry.entity.Category;
import com.jerry.mapper.CategoryMapper;
import com.jerry.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jerry
 * @since 2022-06-27
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
