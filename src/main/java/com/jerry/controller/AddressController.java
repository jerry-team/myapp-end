package com.jerry.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jerry.entity.*;
import com.jerry.service.AddressService;
import com.jerry.service.UserAddressService;
import com.jerry.service.impl.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.helpers.AttributesImpl;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController extends BaseController{
    @Autowired
    private AddressServiceImpl addressService;
    @Autowired
    private AddressService addressService1;
    @Autowired
    private UserAddressService userAddressService;
    @PostMapping("/list")
    public Result selectAddress(@RequestBody Map<String, Object> params){
        User user = (User)request.getAttribute("User");
        QueryWrapper<User> userWrapper = new QueryWrapper<User>();
        //获取用户id
        User user1 = userService.getOne(userWrapper.select("id").eq("username",user.getUsername()));
        Integer uid = user1.getId();
        List<Address> list = addressService.selectByUid(uid);
        System.out.println(Result.succ(list).toString());
        return Result.succ(list);
    }

    @PostMapping("/add")
    public Result addAddress(@RequestBody Map<String, Object> params){
        //params to address
        Address address = new Address();
        address.setName((String)params.get("name"));
        address.setTelephone(Long.valueOf((String) params.get("telephone")));
        address.setAddress((String) params.get("address"));
        address.setDefaultAddress((int) params.get("defaultAddress"));

        User user = (User)request.getAttribute("User");
        QueryWrapper<User> userWrapper = new QueryWrapper<User>();
        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();
        //获取用户id
        User user1 = userService.getOne(userWrapper.select("id").eq("username",user.getUsername()));
        //插入新数据
        addressService1.save(address);
        //获取地址自增id
        Address address1 = addressService1.getOne(addressQueryWrapper.eq("name",address.getName())
                .eq("telephone",address.getTelephone())
                .eq("address",address.getAddress())
                .eq("default_address",address.getDefaultAddress()));
        Integer Aid = address1.getId();
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(user1.getId());
        userAddress.setAddressId(Aid);
        //插入关联表
        userAddressService.save(userAddress);

        /*System.out.println(address1.getId());
        System.out.println(user1.getId());
        System.out.println(user);
        System.out.println(params);*/
        //System.out.println(Result.succ(null));
        return Result.succ(null);
    }
    private static class Address2{
        private Integer id;
        private String name;
        private Long telephone;
        private String address;
        private Integer defaultAddress;
    }
}
