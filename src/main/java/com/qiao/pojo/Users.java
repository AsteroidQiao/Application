package com.qiao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @TableName users
 */
@TableName(value = "users")
@Data
public class Users implements Serializable {
    /**
     * 用户ID自增
     */
    @TableId(type = IdType.AUTO)
    private Integer uid;

    /**
     * 用户账号
     */
    private String uaccount;

    /**
     * 用户姓名
     */
    private String uname;

    /**
     * 用户电话
     */
    private String utel;

    /**
     * 用户性别
     */
    private String ugender;

    /**
     * 用户密码
     */
    private String upwd;

    /**
     * 电子邮箱
     */
    private String uemail;

    /**
     * 注册日期
     */
    private Date uregtime;

    /**
     * 用户积分
     */
    private Double uintegral;

    /**
     * 个人简介
     */
    private String uinfo;

    /**
     * 密保问题
     */
    private String upwdask;

    /**
     * 密保答案
     */
    private String uwdans;

    /**
     * 禁用标识
     */
    private Integer udelete;

    /**
     * 备注
     */
    private String uremarks;

    /**
     * 头像
     */
    private String avatarUrl;

    @TableField(exist = false)
    private String token;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}