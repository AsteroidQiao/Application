package com.qiao.config;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.qiao.pojo.Admins;
import com.qiao.service.AdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTIntercept implements HandlerInterceptor {
    @Autowired
    private AdminsService adminsService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //添加这行代码，让OPTIONS请求通过
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        String token = request.getHeader("token");
        // 执行认证
        if (StrUtil.isBlank(token)) {
            throw new ServiceException(401, "无token，请重新登录");
        }
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 获取 token 中的 admin id
        String adminId;
        try {
            adminId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new ServiceException(401, "token验证失败，请重新登录");
        }
        // 根据token中的admin id查询数据库
        Admins admin = adminsService.getById(adminId);
        if (admin == null) {
            throw new ServiceException(HttpServletResponse.SC_NOT_FOUND, "用户不存在，请重新登录");
        }
        // 用户密码设置成sign验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(admin.getApwd())).build();
        try {
            jwtVerifier.verify(token); // 验证token
        } catch (JWTVerificationException e) {
            throw new ServiceException(401, "token验证失败，请重新登录");
        }
        return true;

    }
}
