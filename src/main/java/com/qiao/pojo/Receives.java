package com.qiao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName receives
 */
@TableName(value ="receives")
@Data
public class Receives implements Serializable {
    /**
     * 收货地址ID自增
     */
    @TableId(type = IdType.AUTO)
    private Integer recid;

    /**
     * 用户ID User表外键
     */
    private Integer ruid;

    /**
     * 收货地址
     */
    private String readdress;

    /**
     * 详细地址
     */
    private String rectreet;

    /**
     * 收货电话
     */
    private String rephone;

    /**
     * 是否默认
     */
    private Integer recisdefault;

    /**
     * 收货人
     */
    private String recreceiver;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 收货省份
     */
    private String recprovince;

    /**
     * 收货城市
     */
    private String reccity;

    /**
     * 收货地区
     */
    private String recdistrict;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}