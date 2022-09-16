package com.jerry.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity
{
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Date createTime;

    private Date updateTime;
}
