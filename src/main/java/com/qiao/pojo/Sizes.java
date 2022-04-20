package com.qiao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sizes
 */
@TableName(value ="sizes")
@Data
public class Sizes implements Serializable {
    /**
     * 尺寸ID
     */
    @TableId(type = IdType.AUTO)
    private Integer sizeid;

    /**
     * 尺寸大小
     */
    private Double sizenum;

    /**
     * 备注
     */
    private String sremarks;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}