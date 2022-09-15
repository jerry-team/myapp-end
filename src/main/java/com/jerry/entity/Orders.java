package com.jerry.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Orders{

    private static final long serialVersionUID = 1L;
    @TableId(value = "id")
    private Integer id;

    private Integer userId;

    private Integer shopId;

    private Double totalAmount;

    /**
     * 0:待支付 1:待发货 2:待收货 3:待评价 4:已评价 5:已关闭 -1:取消订单(0-1) -2:申请退单(2-3) -3:管理员审核通过 -4:管理员直接退单
     */
    private Integer status;

    private Integer closeType;

    private String userName;

    private String userPhone;

    private String userAddress;

    private String reason;

    private Date finishTime;

    private Date cancelTime;

    private Date createTime;

    private Date updateTime;

    @TableField(exist = false)
    private List<OrderItem> orderItemList;
}
