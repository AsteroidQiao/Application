package com.qiao.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.qiao.config.ResponseResult;
import com.qiao.pojo.Admins;
import com.qiao.pojo.Users;
import com.qiao.service.impl.AdminsServiceImpl;
import com.qiao.service.impl.UsersServiceImpl;
import com.qiao.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.hutool.core.lang.TypeReference;
import java.util.List;

@RestController
@RequestMapping("/AdminController")
public class AdminController {
    @Autowired
    private AdminsServiceImpl service;
    @Autowired
    private UsersServiceImpl usersService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/AdminsLogin")
    public ResponseResult AdminsLogin(String acount, String apwd) {
        Admins one = service.getBaseMapper().selectOneByAcountAndApwd(acount, apwd);
        if (one != null) {
            String token = JWTUtils.setToken(one.getAid().toString(), apwd);
            one.setToken(token);
            return ResponseResult.okResult(200, "登录成功", one);
        } else {
            return ResponseResult.okResult(201, "登录失败");
        }
    }
    @GetMapping("/AdminRegister")
    public ResponseResult register(Admins admins) {
        int data = service.getBaseMapper().insertAll(admins);
        return data != 0 ? ResponseResult.okResult(200, "注册成功", data) : ResponseResult.okResult(201, "注册失败");
    }

    @GetMapping("/getAllAdmin")
    public ResponseResult getAllAdmin() {
        List<Admins> data = service.getBaseMapper().getAllAdmin();
        return data != null ? ResponseResult.okResult(200, "成功", data) : ResponseResult.okResult(404, "未找到");
    }


    @GetMapping("/getAllUser")
    public ResponseResult getAllUser() {
        List<Users> data = usersService.getBaseMapper().getAllUser();
        flushRedis("Test");
        return data != null ? ResponseResult.okResult(200, "成功", data) : ResponseResult.okResult(404, "未找到");
    }
    @GetMapping("/clean")
    public ResponseResult clean() {
        flushRedis("Test");
        return ResponseResult.okResult(200, "缓存清除成功");
    }
    // 设置缓存
    @GetMapping("/AdminCache")
    private ResponseResult AdminCache() {
        // 1. 从缓存获取数据
        String jsonStr = stringRedisTemplate.opsForValue().get("Test");
        List<Admins> files;
        if (StrUtil.isBlank(jsonStr)) {  // 2. 取出来的json是空的
            files = service.getBaseMapper().getAllAdmin();  // 3. 从数据库取出数据
            // 4. 再去缓存到redis
            stringRedisTemplate.opsForValue().set("Test", JSONUtil.toJsonStr(files));
        } else {
            // 减轻数据库的压力
            // 5. 如果有, 从redis缓存中获取数据
            files = JSONUtil.toBean(jsonStr, new TypeReference<List<Admins>>() {
            }, true);
        }
        return ResponseResult.okResult(200,"成功",files);

    }

    // 删除缓存
    private void flushRedis(String key) {
        stringRedisTemplate.delete(key);
    }

}
