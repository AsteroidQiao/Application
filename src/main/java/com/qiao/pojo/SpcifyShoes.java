package com.qiao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName spcify_shoes
 */
@TableName(value ="spcify_shoes")
@Data
public class SpcifyShoes implements Serializable {
    /**
     * 定制鞋 ID
     */
    @TableId(type = IdType.AUTO)
    private Integer spsid;

    /**
     * 类型 ID types 表外键
     */
    private Integer tid;

    /**
     * 品牌 ID brands 表外键
     */
    private Integer bid;

    /**
     * 鞋子编号
     */
    private String spsseq;

    /**
     * 鞋子名称
     */
    private String spsname;

    /**
     * 鞋子价格
     */
    private String spsprices;

    /**
     * 详细信息
     */
    private String spscontent;

    /**
     * 部件数量
     */
    private Integer spspartnum;

    /**
     * 部件信息
     */
    private String spspartinfo;

    /**
     * 鞋子性别
     */
    private String spsgender;

    /**
     * 定制 CSS
     */
    private Integer spscss;

    /**
     * 备注
     */
    private String spremarks;

    /**
     * 鞋子大小
     */
    private Integer size;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}