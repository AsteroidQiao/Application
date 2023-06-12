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
     * 瀹氬埗闉?ID
     */
    @TableId(type = IdType.AUTO)
    private Integer spsid;

    /**
     * 绫诲瀷 ID types 琛ㄥ閿?
     */
    private Integer spTid;

    /**
     * 鍝佺墝 ID brands 琛ㄥ閿?
     */
    private Integer spBid;

    /**
     * 闉嬪瓙缂栧彿
     */
    private String spsseq;

    /**
     * 闉嬪瓙鍚嶇О
     */
    private String spsname;

    /**
     * 闉嬪瓙浠锋牸
     */
    private Double spsprices;

    /**
     * 璇︾粏淇℃伅
     */
    private String spscontent;

    /**
     * 閮ㄤ欢鏁伴噺
     */
    private Integer spspartnum;

    /**
     * 閮ㄤ欢淇℃伅
     */
    private String spspartinfo;

    /**
     * 闉嬪瓙鎬у埆
     */
    private String spsgender;

    /**
     * 瀹氬埗 CSS
     */
    private Integer spscss;

    /**
     * 澶囨敞
     */
    private String spremarks;
    /**
     * 澶囨敞
     */
    private Integer uid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}