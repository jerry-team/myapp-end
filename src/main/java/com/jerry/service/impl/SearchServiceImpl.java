package com.jerry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jerry.entity.Search;
import com.jerry.mapper.SearchMapper;
import com.jerry.service.SearchService;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl extends ServiceImpl<SearchMapper, Search> implements SearchService {
}
