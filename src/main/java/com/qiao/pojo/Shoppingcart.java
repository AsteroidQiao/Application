package com.qiao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName shoppingcart
 */
@TableName(value ="shoppingcart")
@Data
public class Shoppingcart implements Serializable {
    /**
     * 用户id
     */
    @TableId
    private Integer uid;

    /**
     * shoes 鞋id
     */
    private Integer sid;

    /**
     * shoes 鞋数量
     */
    private Integer scount;

    /**
     * 鞋码
     */
    private Double size;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}