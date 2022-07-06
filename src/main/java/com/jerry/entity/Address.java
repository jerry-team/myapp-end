package com.jerry.entity;

import lombok.Data;

@Data
public class Address extends BaseEntity{
    private String name;
    private Long telephone;
    private String address;
    private Integer defaultAddress;
}
