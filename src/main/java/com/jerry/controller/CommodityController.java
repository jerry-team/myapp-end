package com.jerry.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jerry.annotation.OperationLogAnnotation;
import com.jerry.common.vo.UserVO;
import com.jerry.entity.*;
import com.jerry.utils.PageUtils;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jerry
 * @since 2022-06-27
 */
@RestController
@RequestMapping("/commodity")
public class CommodityController extends BaseController {

    @PostMapping("/list")
    public Result list(){
        return Result.succ(commodityService.list());
    }

    @PostMapping("/listByCategoryId")
    public Result list(@RequestBody Map<String, Object> params){
        QueryWrapper<Commodity> qw = new QueryWrapper<Commodity>();
        List<Commodity> commodityList = commodityService.list(qw.eq("category",params.get("categoryId")));
        return Result.succ(commodityList);
    }

//    @OperationLogAnnotation(operType = "点击")
    @PostMapping("/listById")
    public Result listById(@RequestBody Map<String, Object> params){
        QueryWrapper<Commodity> qw = new QueryWrapper<Commodity>();
        Commodity commodity = commodityService.getById((Serializable) params.get("commodityId"));

        return Result.succ(commodity);
    }

    @PostMapping("/pageQuery")
    public Result pageQuery(@RequestBody Map<String, Object> params)
    {

        Page<Commodity> page = new Page<>((Integer)params.get("start"), (Integer)params.get("pageSize"));
        if((Integer)params.get("categoryId") <= 1)
            return Result.succ(commodityMapper.selectPage(page,null).getRecords());
        else
        {
            QueryWrapper<Commodity> wrapper = new QueryWrapper<Commodity>();
            return Result.succ(commodityMapper.selectPage(page,wrapper.eq("category",(Integer)params.get("categoryId")-1)).getRecords());
        }
    }

    @CrossOrigin
    @PostMapping("/backendPageQuery")
    public Result getInfo(@RequestBody PageInfo req) {
        {
            List<Commodity> commodityList = commodityMapper.getCommodityByShopId(req.getShopId());


            if(commodityList.size() == 0){                                                                                          
                return Result.fail("没有相关数据！");
            }

            PageUtils pageUtils = new PageUtils();
            Page<Commodity> page = pageUtils.getPages(req.get_currentPage(),req.getPageSize(),commodityList);
            commodityList = page.getRecords();

            for (Commodity item:commodityList
                 ) {
                item.setCategoryName(commodityMapper.getCategoryInfo(item.getCategory()));
                item.setShopName(commodityMapper.getShopName(item.getId()));
            }
            return Result.succ(commodityList);
        }
    }

    @CrossOrigin
    @PostMapping("/backendPageQueryAll")
    public Result getInfoAll(@RequestBody PageInfo req) {
        {
//            List<Commodity> commodityList = commodityMapper.getCommodityByShopId(req.getShopId());
            Page page = new Page(req.get_currentPage(),req.getPageSize());

            List<Commodity> commodityList = commodityMapper.selectPage(page, null).getRecords();

            for (Commodity item:commodityList
                 ) {
                item.setCategoryName(commodityMapper.getCategoryInfo(item.getCategory()));
                item.setShopName(commodityMapper.getShopName(item.getId()));
            }
            return Result.succ(commodityList);
        }
    }

    @CrossOrigin
    @PostMapping("/backendGetRow")
    public Result getRow(){
        return Result.succ(commodityMapper.getCommodityRow());
    }

    @CrossOrigin
    @DeleteMapping("/backendDelete/{id}")
    public Result deleteCommodity(@PathVariable("id") Integer id){
        QueryWrapper<Commodity> cm = new QueryWrapper<>();
        cm.eq("id",id);
        if(commodityMapper.delete(cm) == 1)
            return Result.succ("删除成功");
        else
            return Result.fail("删除失败，并发错误");
    }

    @CrossOrigin
    @PostMapping("/updateCommodity")
    public Result editInfo(@RequestBody Map<String, Object> req){
        QueryWrapper<Commodity> cm = new QueryWrapper<>();
        Commodity commodity = new Commodity();


        JSONObject json1=JSONObject.parseObject(JSON.toJSONString(req.get("form")));
        Map<String, Object> form = json1;


        JSONObject json2=JSONObject.parseObject(JSON.toJSONString(req.get("pageInfo")));
        Map<String, Object> pageInfo = json2;


        commodity.setPrice(Double.parseDouble(form.get("price").toString()));

        if(form.get("description") != null)
            commodity.setDescription(form.get("description").toString());
        else
            commodity.setDescription("");

        commodity.setCategory(Integer.parseInt(form.get("category").toString()));

        if(form.get("imgurl")!=null)
            commodity.setImgurl(form.get("imgurl").toString());
        else
            commodity.setImgurl("");

        if(form.get("videourl")!=null)
            commodity.setVideourl(form.get("videourl").toString());
        else
            commodity.setVideourl("");

        commodity.setNumber(Integer.parseInt(form.get("number").toString()));

        if(form.get("breed")!=null)
            commodity.setBreed(form.get("breed").toString());
        else
            commodity.setVideourl("");


            commodity.setIsPest(Integer.parseInt(form.get("isPest").toString()));



            commodity.setIsPedigree(Integer.parseInt(form.get("isPedigree").toString()));

            commodity.setVaccin(Integer.parseInt(form.get("vaccin").toString()));


//        commodity.setImgurl(req.get(""));
//
//
//        commodity.setShopName(req.getName());
//        commodity.setPrice(req.getPrice());
//        commodity.setDescription(req.getDescription());
//        commodity.setCategory(req.getCategory());
//        commodity.setImgurl(req.getImgurl());
//        commodity.setVideourl(req.getVideourl());
//        commodity.setNumber(req.getNumber());
//        commodity.setBreed(req.getBreed());
//        commodity.setIsPedigree(req.getIsPedigree());
//        commodity.setIsPest(req.getIsPest());
//        commodity.setVaccin(req.getVaccin());
//
        cm.eq("id",Integer.parseInt((form.get("id").toString())));
//        Integer pageInfo_shopId = ;
        if(commodityMapper.update(commodity,cm) == 1 && pageInfo.get("shopId") == null){

            Page page = new Page(Integer.parseInt(pageInfo.get("_currentPage").toString()),Integer.parseInt(pageInfo.get("pageSize").toString()));


            List<Commodity> commodityList = commodityMapper.selectPage(page, null).getRecords();

            for (Commodity item:commodityList
            ) {
                item.setCategoryName(commodityMapper.getCategoryInfo(item.getCategory()));
                item.setShopName(commodityMapper.getShopName(item.getId()));
            }
            return Result.succ(commodityList);

        }else if(commodityMapper.update(commodity,cm) == 1 && pageInfo.get("shopId") != null){
            List<Commodity> commodityList = commodityMapper.getCommodityByShopId(Integer.parseInt(pageInfo.get("shopId").toString()));


            if(commodityList.size() == 0){
                return Result.fail("没有相关数据！");
            }

            PageUtils pageUtils = new PageUtils();

            Page<Commodity> page = pageUtils.getPages(Integer.parseInt(pageInfo.get("_currentPage").toString()),
                                                    Integer.parseInt(pageInfo.get("pageSize").toString()),
                                                    commodityList);
            commodityList = page.getRecords();

            for (Commodity item:commodityList
            ) {
                item.setCategoryName(commodityMapper.getCategoryInfo(item.getCategory()));
                item.setShopName(commodityMapper.getShopName(item.getId()));
            }
            return Result.succ(commodityList);


        }
        return Result.fail("更新失败,找不到相关数据");





    }


}
