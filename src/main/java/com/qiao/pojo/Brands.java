package com.qiao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName brands
 */
@TableName(value ="brands")
@Data
public class Brands implements Serializable {
    /**
     * 品牌ID自增
     */
    @TableId(type = IdType.AUTO)
    private Integer bid;

    /**
     * 品牌名称
     */
    private String bname;

    /**
     * 男鞋、女鞋
     */
    private String bsex;

    /**
     * 是否上线品牌
     */
    private Integer bstate;

    /**
     * 备注
     */
    private String bremarks;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}