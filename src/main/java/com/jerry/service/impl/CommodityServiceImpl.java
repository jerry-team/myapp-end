package com.jerry.service.impl;

import com.jerry.entity.Comment;
import com.jerry.entity.Commodity;
import com.jerry.mapper.CommentMapper;
import com.jerry.mapper.CommodityMapper;
import com.jerry.service.CommodityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jerry.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jerry
 * @since 2022-06-27
 */
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements CommodityService {

//        @Autowired
//        UserService userService;
//        @Autowired
//        CommodityService commodityService;
//        @Autowired
//        CommentMapper commentMapper;

    @Autowired
    CommodityMapper commodityMapper;

    @Override
    public String getShopName(Integer cid){
        return commodityMapper.getShopName(cid);
    }

    @Override
    public List<Commodity> getRecommend(Integer userId) {
            //获取商品总数量
//            int commoditySize = commodityService.count();
//            int userSize = userService.count();
////            List<Comment> commentList = commentMapper,
//            int[][] ucScoreArray = new int[userSize][commoditySize];

        return null;
    }

    @Override
    public List<Commodity> getByShopId(Integer shopId) {
        return commodityMapper.getByShopId(shopId);
    }

    @Override
    public String getCategoryInfo(Integer categoryId){
            return commodityMapper.getCategoryInfo(categoryId);
    }

    @Override
    public Integer getCommodityRow(){
            return commodityMapper.getCommodityRow();
    }

    @Override
    public List<Commodity> getCommodityByShopId(Integer sid){
            return commodityMapper.getCommodityByShopId(sid);
    }

    @Override
    public List<Commodity> getAllCommodity(){
            return commodityMapper.getAllCommodity();
    }
}
