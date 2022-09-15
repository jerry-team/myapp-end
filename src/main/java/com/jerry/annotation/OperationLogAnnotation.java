package com.jerry.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)//注解放置的目标位置即方法级别
@Retention(RetentionPolicy.RUNTIME)//注解在哪个阶段执行
@Documented
public @interface OperationLogAnnotation {
//    String operModul() default ""; // 操作模块
//
    String operType() default "其它";  // 操作类型
//
//    Integer operCommodityId() default -1;  // 操作的物品id
}
