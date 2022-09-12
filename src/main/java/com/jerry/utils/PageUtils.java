package com.jerry.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;

public class PageUtils {

    public static Page getPages(Integer currentPage, Integer pageSize, List list){

        Page page = new Page();

        int size = list.size();

        if(pageSize > size){
            pageSize = size;
        }


        //求出最大页数，防止currentPage越界
        int maxPage = size % pageSize == 0 ? size / pageSize : size / pageSize + 1;

        if(currentPage > maxPage){
            currentPage = maxPage;
        }

        //当前页第一条数据下标
        int curIds = currentPage > 1 ? (currentPage -1) * pageSize : 0;

        List pageList = new ArrayList<>();

        //将当前页的数据放进pageList
        for (int i = 0; i < pageSize && curIds + i < size; i++) {
            pageList.add(list.get(curIds + i));
        }

        page.setCurrent(currentPage).setSize(pageSize).setTotal(list.size()).setRecords(pageList);

        return page;

    }

}
