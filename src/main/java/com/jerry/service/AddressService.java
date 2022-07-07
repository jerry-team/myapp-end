package com.jerry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jerry.entity.Address;
import com.jerry.entity.Category;
import org.apache.ibatis.annotations.Param;

public interface AddressService extends IService<Address> {

    Address getByUserId(@Param("user_id") Integer uid);
}
