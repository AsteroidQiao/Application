package com.qiao.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName files
 */
@TableName(value ="files")
@Data
public class Files implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件大小(kb)
     */
    private Long size;

    /**
     * 下载链接
     */
    private String url;

    /**
     * 文件md5
     */
    private String md5;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 是否禁用链接
     */
    private Integer enable;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}