package com.jerry.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

    private String desription;

    private Integer category;

    private Integer number;

    @TableField("imgUrl")
    private String imgurl;

    @TableField("videoUrl")
    private String videourl;


}