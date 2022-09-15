package com.jerry.myapp.entity;

import com.jerry.entity.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class OrdersDTO {

    private Integer shopId;

    private Double totalAmount;

    private List<OrderItemDTO> orderItemDTOList;
}
