package com.jerry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jerry.controller.AddressController;
import com.jerry.entity.Address;
import com.jerry.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressMapper extends BaseMapper<Address> {

    @Select("SELECT A.name,A.telephone,A.address,A.default_address " +
            "From user U, address A, user_address UA "+
            "WHERE UA.address_id=A.id AND UA.user_id=U.id AND U.id = #{user_id}")
    List<Address> selectByUid(@Param("user_id") Integer uid);
    /*@Insert("insert into address (name,telephone,address,default_address) values (#{name},#{telephone},#{address},#{de})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    Address insertAddress(@Param("address")Address address);*/
    /*@Select("SELECT id FROM address WHERE name = #{address.name} and telephone = #{address.telephone} and default_address = #{address.defaultAddress}")
    Integer selectAid(@Param("address") Address address);*/

    @Select("select * from address a where a.id in " +
            "(select address_id from user_address where user_id = #{uid}) and " +
            "a.default_address = 1")
    Address getByUserId(Integer uid);
}
