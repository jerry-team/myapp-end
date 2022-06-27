package com.jerry.common.vo;

import com.jerry.entity.User;
import lombok.Data;

@Data
public class UserVO {
    private String username;

    private String password;

    private String telephone;

    private String nickname;

    private String icon;

    private String email;

    private String token;

    public UserVO(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.telephone = user.getTelephone();
        this.nickname = user.getNickname();
        this.icon = user.getIcon();
        this.email =  user.getEmail();
    }
}
