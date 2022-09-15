package com.jerry.entity;

import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jerry
 * @since 2022-09-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer orderId;

    private Integer commodityId;

    private String name;

    private String img;

    private BigDecimal price;

    /**
     * 数量
     */
    private Integer num;

    private Double totalAmount;

    /**
     * 是否评价
     */
    private Integer isComment;


}
