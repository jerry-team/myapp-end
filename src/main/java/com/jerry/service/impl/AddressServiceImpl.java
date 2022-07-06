package com.jerry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jerry.entity.Address;

import com.jerry.mapper.AddressMapper;
import com.jerry.service.AddressService;
import com.jerry.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {
    @Autowired
    AddressMapper addressMapper;
    public List<Address> selectByUid(Integer uid){
        return addressMapper.selectByUid(uid);
    }
    /*public Address insertAddress(Address address){
        String name = address.getName();
        Long telephone = address.getTelephone();
        String address1 = address.getAddress();
        Integer Default = address.getDefaultAddress();
        return addressMapper.insertAddress(name,telephone,address1,Default);
    }*/
    /*public Integer selectAddressByAddress(Address address){
        return addressMapper.selectAid(address);
    }*/
}
