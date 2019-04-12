package com.xm.mall.utils;

/**
 * @author huyan
 * @date 2019-04-09 15:40
 */
public enum  ResultCode {

    SUCCESS(200,"success"),
    REQUEST_ERROR(404,"请求异常"),
    SERVICE_ERROR(500,"服务器异常"),
    PARAM_ERROR(400,"数据绑定异常"),
    METHOD_ERROR(405,"请求类型异常"),

    ERROR(201,"error");

    ResultCode(int code,String msg){
        this.code = code;
        this.msg = msg;
    }


    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
