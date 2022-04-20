package com.qiao.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.qiao.pojo.Admins;
import com.qiao.pojo.Users;
import com.qiao.service.AdminsService;
import com.qiao.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import cn.hutool.core.date.DateUtil;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


public class JWTUtils {
    private static UsersService staticUsersService;
    @Autowired
    private UsersService usersService;

    @PostConstruct
    public void setUsersService() {
        staticUsersService = usersService;
    }

    /**
     * @param ‘id’  id
     * @param ‘sign’ sign
     * @return token
     */

    public static String setToken(String id, String sign) {
        return JWT.create().withAudience(id).withExpiresAt(DateUtil.offsetHour(new Date(), 2)).sign(Algorithm.HMAC256(sign));
    }


    /**
     * 获取user
     * @param 'token' token字符串
     * @return 解析后的token
     */
    public static Users getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        String id = JWT.decode(token).getAudience().get(0);
        return staticUsersService.getBaseMapper().selectById(Integer.parseInt(id));

    }

    /**
     * 解析token
     * 验证token是否合法
     */
    public static DecodedJWT decode(String token, String sign) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(sign)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT;
    }

}
