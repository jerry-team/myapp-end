package com.jerry.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jerry
 * @since 2022-06-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Commodity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private Double price;

    private String description;

    private Integer category;

    private Integer number;

    private String imgurl;

    @TableField("videoUrl")
    private String videourl;

    @TableField("vaccin")
    private Integer vaccin;

    @TableField("isPedigree")
    private Integer isPedigree;

    @TableField("isPest")
    private Integer isPest;

    @TableField("breed")
    private String breed;

    @TableField(exist = false)
    private boolean isChecked = false;

    @TableField(exist = false)
    private String shopName;

    @TableField(exist = false)
    private String categoryName;



}
