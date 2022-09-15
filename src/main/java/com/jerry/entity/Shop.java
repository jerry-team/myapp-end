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
 * @since 2022-06-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Shop extends BaseEntity {
    @TableField("id")
    private Integer id;

    @TableField("shop_name")
    private String shopName;

    @TableField("shop_address")
    private String shopAddress;

    @TableField("description")
    private String description;


}
