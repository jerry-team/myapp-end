package com.jerry.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jerry
 * @since 2022-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ShopCommodity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer commodityId;

    private Integer shopId;


}
