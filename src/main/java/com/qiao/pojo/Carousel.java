package com.qiao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName carousel
 */
@TableName(value ="carousel")
@Data
public class Carousel implements Serializable {
    /**
     * 轮播图序号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 照片url
     */
    private String url;

    /**
     * 跳转路由
     */
    private String route;

    /**
     * 描述
     */
    private String info;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}