package com.qiao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * @TableName admins
 */
@TableName(value = "admins")
@Data
public class Admins implements Serializable {
    /**
     * 管理员ID
     */
    @TableId(type = IdType.AUTO)
    private Integer aid;

    /**
     * 账户
     */
    private String acount;

    /**
     * 密码
     */
    private String apwd;

    /**
     * 权限 permission 外键
     */
    private Integer aPcid;

    /**
     * 备注
     */
    private String aremarks;
    @TableField(exist = false)
    private String token;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}