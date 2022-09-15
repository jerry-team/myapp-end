package com.jerry.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("orders")
public class BackendOrder extends BaseEntity{
    private static final long serialVersionUID = 1L;
    private Double totalAmount;
    private Integer userId;
    private Integer status;

    @TableField(value = "close_type",fill = FieldFill.UPDATE)
    private Integer closeType;
    private String userName;
    private String userPhone;
    private String userAddress;
    private String reason;

    @TableField(exist = false)
    private String shopName;

    @TableField(exist = false)
    private Integer searchTotal;


}
