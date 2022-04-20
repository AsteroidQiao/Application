package com.qiao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName orders
 */
@TableName(value ="orders")
@Data
public class Orders implements Serializable {
    /**
     * 订单ID自增
     */
    @TableId(type = IdType.AUTO)
    private Integer oid;

    /**
     * 用户ID users表外键
     */
    private Integer ouid;

    /**
     * 商品shoesid外键
     */
    private Integer sid;

    /**
     * 订单数量
     */
    private Integer ocount;

    /**
     * 收货地址ID receives表外键
     */
    private Integer orecid;

    /**
     * 订单编号
     */
    private String onum;

    /**
     * 订单状态
     */
    private Integer ostate;

    /**
     * 下单时间
     */
    private Date ordertime;

    /**
     * 总价
     */
    private Double ototal;

    /**
     * 快递跟踪
     */
    private String oexpinfo;

    /**
     * 总积分
     */
    private String ointegral;

    /**
     * 备注
     */
    private String onote;

    /**
     * 鞋码
     */
    private Double size;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}