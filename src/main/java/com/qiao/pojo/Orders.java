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
     * 璁㈠崟ID鑷
     */
    @TableId(type = IdType.AUTO)
    private Integer oid;

    /**
     * 鐢ㄦ埛ID users琛ㄥ閿?
     */
    private Integer ouid;

    /**
     * 鏀惰揣鍦板潃ID receives琛ㄥ閿?
     */
    private Integer orecid;

    /**
     * 璁㈠崟缂栧彿
     */
    private String onum;

    /**
     * 璁㈠崟鐘舵€?
     */
    private Integer ostate;

    /**
     * 涓嬪崟鏃堕棿
     */
    private Date ordertime;

    /**
     * 鎬讳环
     */
    private Double ototal;

    /**
     * 蹇€掕窡韪?
     */
    private String oexpinfo;

    /**
     * 鎬荤Н鍒?
     */
    private String ointegral;

    /**
     * 澶囨敞
     */
    private String onote;

    /**
     * 
     */
    private Double size;

    /**
     * 
     */
    private Integer sid;

    /**
     * 
     */
    private Integer ocount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}