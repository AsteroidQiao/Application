package com.qiao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName types
 */
@TableName(value ="types")
@Data
public class Types implements Serializable {
    /**
     * 类型ID自增
     */
    @TableId(type = IdType.AUTO)
    private Integer tid;

    /**
     * 类型名称
     */
    private String tname;

    /**
     * 注释
     */
    private String tremarks;

    /**
     * 禁用标识
     */
    private Integer tdelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}