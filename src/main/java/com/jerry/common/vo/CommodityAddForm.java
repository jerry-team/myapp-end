package com.jerry.common.vo;

import lombok.Data;

@Data
public class CommodityAddForm {
    private String name;
    private Double price;
    private String description;
    private Integer category;
    private Integer number;
    private String imgUrl;
    private Integer vaccin;
    private Integer isPedigree;
    private Integer isPest;
    private String breed;
    private Integer shop;
}
