package com.jerry.controller;


import com.jerry.entity.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jerry
 * @since 2022-06-27
 */
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {

    @PostMapping("/list")
    public Result list(){
        return Result.succ(categoryService.list());
    }

}
