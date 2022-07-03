package com.jerry.entity;

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

    private static final long serialVersionUID = 1L;

    private String name;

    private String shopAddress;


}
