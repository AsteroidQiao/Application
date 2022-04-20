package com.qiao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName shoe_photos
 */
@TableName(value ="shoe_photos")
@Data
public class ShoePhotos implements Serializable {
    /**
     * 图片ID
     */
    @TableId(type = IdType.AUTO)
    private Integer spid;

    /**
     * 鞋子 ID shoes 表外键
     */
    private Integer spSid;

    /**
     * 图片 URL
     */
    private String spurl;

    /**
     * 备注
     */
    private String spremarks;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}