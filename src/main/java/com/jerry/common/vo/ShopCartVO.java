package com.jerry.common.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.jerry.entity.Commodity;
import com.jerry.entity.Shop;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ShopCartVO {

    private Integer id;

    private String name;

    private String shopAddress;

    private String description;

    private List<Commodity> commodityList;

    private boolean isChecked;//商品是否选中

//    private Date createTime;
//
//    private Date updateTime;

    public ShopCartVO(Shop shop) {
        this.id = shop.getId();
        this.name = shop.getShopName();
        this.shopAddress = shop.getShopAddress();
        this.description = shop.getDescription();
//        this.createTime = shop.getCreateTime();
//        this.updateTime = shop.getUpdateTime();
    }

    public void setCommodityList(List<Commodity> commodityList){
        this.commodityList = commodityList;
    }
}
