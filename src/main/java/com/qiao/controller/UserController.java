package com.qiao.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qiao.config.ResponseResult;
import com.qiao.pojo.Users;
import com.qiao.service.impl.UsersServiceImpl;
import com.qiao.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/UserControl")
public class UserController {
    @Autowired
    private UsersServiceImpl service;

    @PostMapping("/UserLogin")
    public ResponseResult UserLogin(String uaccount, String upwd) {

        Users one = service.getBaseMapper().selectOneByUaccountAndUpwd(uaccount, upwd);
        if (one != null) {
            String token = JWTUtils.setToken(one.getUid().toString(), upwd);
            one.setToken(token);
            return ResponseResult.okResult(200, "登录成功", one);
        } else {
            return ResponseResult.okResult(201, "登录失败");
        }
    }

    @PostMapping("/UserRegister")
    public ResponseResult UserRegister(String uaccount, String upwd, String uemail) {
        Users one = service.getBaseMapper().selectByUaccount(uaccount);
        if (one == null) {
            Users user = new Users();
            user.setUaccount(uaccount);//用户账号名
            user.setUname(uaccount);//用户名，和uaccount一致，但可以修改
            user.setUpwd(upwd);//用户密码
            user.setUemail(uemail);//用户email
            user.setUregtime(DateUtil.date());//用户注册时间
            int i = service.getBaseMapper().insertAll(user);
            if (i != 0) {
                return ResponseResult.okResult(200, "注册成功");
            } else {
                return ResponseResult.okResult(201, "注册失败");
            }
        } else {
            return ResponseResult.okResult(201, "用户已存在！");
        }
    }

    @PostMapping("/UserUpdate")
    public ResponseResult UserUpdate(@RequestBody Users user) {
        UpdateWrapper<Users> updateWrapper = new UpdateWrapper<>();
        updateWrapper.
                set("uname", user.getUname()).
                set("utel", user.getUtel()).
                set("uinfo", user.getUinfo()).
                set("avatarUrl", user.getAvatarUrl()).
                set("upwdask", user.getUpwdask()).
                set("uwdans", user.getUwdans()).
                set("uinfo", user.getUinfo()).
                eq("uaccount", user.getUaccount());
        boolean update = service.update(null, updateWrapper);
        Users NewUser = service.getBaseMapper().selectByUaccount(user.getUaccount());
        if (update) {
            return ResponseResult.okResult(200, "修改成功", NewUser);
        } else {
            return ResponseResult.okResult(201, "修改失败");
        }
    }

    @PostMapping("/UserUpdatepwd")
    public ResponseResult UserUpdatepwd(String uaccount, String oldpwd, String newpwd) {
        UpdateWrapper<Users> updatepwdWrapper = new UpdateWrapper<>();
        updatepwdWrapper.eq("uaccount", uaccount).eq("upwd", oldpwd).set("upwd", newpwd);
        boolean update = service.update(null, updatepwdWrapper);
        if (update) {
            return ResponseResult.okResult(200, "修改成功");
        } else {
            return ResponseResult.okResult(201, "修改失败，密码输入错误");
        }
    }

    @PostMapping("/UserUpdatepwd2")
    public ResponseResult UserUpdatepwd2(String uaccount, String newpwd) {
        UpdateWrapper<Users> updatepwdWrapper = new UpdateWrapper<>();
        updatepwdWrapper.eq("uaccount", uaccount).set("upwd", newpwd);
        boolean update = service.update(null, updatepwdWrapper);
        if (update) {
            return ResponseResult.okResult(200, "修改成功");
        } else {
            return ResponseResult.okResult(201, "修改失败");
        }
    }

    @PostMapping("/getUser")
    public ResponseResult getUser(@RequestParam String uaccount) {
        Users users = service.getBaseMapper().selectByUaccount(uaccount);
        if (users == null) {
            return ResponseResult.okResult(200, "查找失败");
        } else {
            return ResponseResult.okResult(201, users);
        }
    }


}
