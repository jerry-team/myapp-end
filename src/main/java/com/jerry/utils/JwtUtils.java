package com.jerry.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
@ConfigurationProperties(prefix = "jerry.jwt")
public class JwtUtils {

    private long expire;
    private String secret;
    private String header;

    // 生成jwt
    public String generateToken(String username,Integer privilege) {
        String role;
        if(privilege == 1){
            role = "会员用户";
        }else{
            role = "普通用户";
        }
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + 1000 * expire);

        return Jwts.builder()
                //
                .setHeaderParam("typ", "JWT")
                //主体
                .setSubject(username)
                //判断权限等级并设置
                .claim("role",role)
                .claim("state",privilege)
                //创建时间
                .setIssuedAt(nowDate)
                //过期时间
                .setExpiration(expireDate)// 7天過期
                //加密算法,secret:密钥
                .signWith(SignatureAlgorithm.HS512, secret)
                //合成
                .compact();
    }



    // 解析jwt
    public Claims getClaimByToken(String jwt) {
        //加异常是为了后续代码能正常进行
        try {
            return Jwts.parser()
                    //设置密钥
                    .setSigningKey(secret)
                    //进行解析
                    .parseClaimsJws(jwt)
                    //得到Body部分
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    // jwt是否过期
    public boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    public void parseToken( String token ) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

}
