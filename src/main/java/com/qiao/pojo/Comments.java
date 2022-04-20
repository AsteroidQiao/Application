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
 * @TableName comments
 */
@TableName(value ="comments")
@Data
public class Comments implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer cid;

    /**
     * 用户的ID users表的外键
     */
    private Integer cuid;

    /**
     * 鞋子的ID shoes表的外外键
     */
    private Integer csid;

    /**
     * 评论内容
     */
    private String sccomments;

    /**
     * 用户评分
     */
    private Integer scscore;

    /**
     * 评论时间
     */
    private Date sctime;

    /**
     * 从属订单号
     */
    private Integer scoid;

    /**
     * 备注
     */
    private String scremarks;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}