package com.qiao.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName second_kills
 */
@TableName(value ="second_kills")
@Data
public class SecondKills implements Serializable {
    /**
     * 主键 ID 自增
     */
    @TableId(type = IdType.AUTO)
    private Integer skid;

    /**
     * 鞋子 ID hoes 表外键
     */
    private Integer skSid;

    /**
     * 消耗积分
     */
    private Double skintegral;

    /**
     * 秒杀数量
     */
    private Integer skamount;

    /**
     * 秒杀开始时间
     */
    private Date skstarttime;

    /**
     * 秒杀持续时间
     */
    private Date skduratoin;

    /**
     * 秒杀是否过期
     */
    @TableLogic
    private Integer skisvalid;

    /**
     * 秒杀尺码
     */
    private Integer sksize;

    /**
     * 备注
     */
    private String skremarks;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}