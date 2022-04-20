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
 * @TableName shoes
 */
@TableName(value ="shoes")
@Data
public class Shoes implements Serializable {
    /**
     * 鞋子ID自增
     */
    @TableId(type = IdType.AUTO)
    private Integer sid;

    /**
     * 类ID type表外键
     */
    private Integer tid;

    /**
     * 品牌ID brands表外键
     */
    private Integer sbid;

    /**
     * 鞋子编号
     */
    private String snum;

    /**
     * 鞋子名称
     */
    private String sname;

    /**
     * 鞋子价格
     */
    private Double sprices;

    /**
     * 鞋库存量
     */
    private Double sdiscount;

    /**
     * 上市时间
     */
    private Date spubtime;

    /**
     * 生产厂商
     */
    private String sproducer;

    /**
     * 性别属性
     */
    private String sgender;

    /**
     * 鞋子颜色
     */
    private String scolor;

    /**
     * 内容简介
     */
    private String sinfo;

    /**
     * 卖出次数
     */
    private Integer stimessold;

    /**
     * 鞋子图片url
     */
    private String simage;

    /**
     * 鞋子详细信息url
     */
    private String sdetail;

    /**
     * 单件鞋总积分
     */
    private Double sintegral;

    /**
     * 鞋禁用标识
     */
    private Integer sdelete;

    /**
     * 备注
     */
    private String sremarks;

    /**
     * 手机端图片
     */
    private String sandroiding;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}