package com.jerry.myapp.entity;

import lombok.Data;

@Data
public class OrderItemDTO {

    private Integer commodityId;

    private Integer num;

    private Double totalAmount;
}
