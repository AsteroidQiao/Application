package com.qiao.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
// 实体类与json互转的时候 属性值为null的不参与序列化
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;
    private T data2;
    private T data3;

    //  ResponseResult () success的code,msg,
    public ResponseResult() {
        this.code = AppHttpCodeEnum.SUCCESS.getCode();
        this.msg = AppHttpCodeEnum.SUCCESS.getMsg();
    }

    //  ResponseResult (data) success的code,msg,和data
    public ResponseResult(T data) {
        this.code = AppHttpCodeEnum.SUCCESS.getCode();
        this.msg = AppHttpCodeEnum.SUCCESS.getMsg();
        this.data = data;
    }

    //  ResponseResult (data) success的code,msg,和data
    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    //  ResponseResult (data) success的code,msg,和data
    public ResponseResult(T data, T data2) {
        this.data = data;
        this.data2 = data2;
    }

    //  ResponseResult (data) success的code,msg,和data
    public ResponseResult(T data, T data2, T data3) {
        this.data = data;
        this.data2 = data2;
        this.data2 = data3;
    }

    //  ResponseResult (code,msg) 自定义code,msg,
    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    //ResponseResult (code,msg,data)可以给自定义code,msg,data
    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 定义方法
     * 很重要
     * 查方法看看范例,直接使用就行
     */

    //使用范例ResponseResult.okResult()         无参数返回SUCCESS
    public static ResponseResult okResult() {
        return new ResponseResult();
    }

    //使用范例ResponseResult.okResult(201, "登录失败")返回自定义的        code和msg
    public static ResponseResult okResult(int code, String msg) {
        return new ResponseResult(code, msg);

    }

    //使用范例ResponseResult.okResult(200, "登录成功", data)返回自定义的      code和msg,数据data
    public static ResponseResult okResult(int code, String msg, Object data) {
        return new ResponseResult(code, msg, data);
    }

    //使用范例ResponseResult.okResult(200, "登录成功", data)返回自定义的数据data data2
    public static ResponseResult okResult(Object data, Object data2) {
        return new ResponseResult(data, data2);
    } //使用范例ResponseResult.okResult(200, "登录成功", data)返回自定义的数据data data2 data3

    public static ResponseResult okResult(Object data, Object data2, ArrayList<Double> data3) {
        return new ResponseResult(data, data2, data3);
    }

    //使用范例ResponseResult.okResult(200, data)返回自定义的      code和msg,数据data
    public static ResponseResult okResult(int code, Object data) {
        return new ResponseResult(code, data);
    }

    //使用范例ResponseResult.okResult(200, "登录成功", data)返回SUCCESS和自定义的      数据data
    public static ResponseResult okResult(Object data) {
        return new ResponseResult(data);
    }

    //使用范例ResponseResult.setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS);   返回enums的code和msg
    public static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums) {
        return okResult(enums.getCode(), enums.getMsg());
    }

    //使用范例ResponseResult.setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS);   返回enums的code和msg,data
    public static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums, Object data) {
        return okResult(enums.getCode(), enums.getMsg(), data);
    }

}
