package com.jerry.entity;

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
public class Category extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private int type;

}
