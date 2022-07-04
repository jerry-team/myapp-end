package com.jerry.entity;

import cn.hutool.core.date.format.FastDateFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Search {
    private Integer uid;
    private String val;
    private Integer snum;
    private FastDateFormat datetime;
}
