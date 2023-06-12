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
     * 鐢ㄦ埛鐨処D users琛ㄧ殑澶栭敭
     */
    private Integer cUid;

    /**
     * 闉嬪瓙鐨処D shoes琛ㄧ殑澶栧閿?
     */
    private Integer cSid;

    /**
     * 璇勮鍐呭
     */
    private String sccomments;

    /**
     * 鐢ㄦ埛璇勫垎
     */
    private Integer scscore;

    /**
     * 璇勮鏃堕棿
     */
    private Date sctime;

    /**
     * 浠庡睘璁㈠崟鍙?
     */
    private Integer scoid;

    /**
     * 澶囨敞
     */
    private String scremarks;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}