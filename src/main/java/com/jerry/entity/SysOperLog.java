package com.jerry.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jerry
 * @since 2022-08-31
 */
@Data
public class SysOperLog{

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("userId")
    private Integer userid;

    @TableField("commodityId")
    private Integer commodityid;

    private String type;

    @TableField("create_time")
    private Date createTime;


}
