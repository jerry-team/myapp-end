package com.jerry.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jerry
 * @since 2022-06-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {


    private String username;

    private String password;

    private String telephone;

    private String nickname;

    private String icon;

    private String email;

    private Integer state;

    private Double money;
}
