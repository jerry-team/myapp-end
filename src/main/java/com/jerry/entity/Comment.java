package com.jerry.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jerry
 * @since 2022-09-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Comment extends BaseEntity {

    @TableField("user_id")
    private Integer userId;

    @TableField(exist = false)
    private String user_name;

    @TableField("commodity_id")
    private Integer commodityId;

    private String content;

    /**
     * 0:顶级
     */
    private Integer parentId;

    @TableField(exist = false)
    private String parent_name;

    /**
     * 0-5分
     */
    private Integer score;

    @TableField(exist = false)
    private List<Comment> replyList = new ArrayList<>();

}
