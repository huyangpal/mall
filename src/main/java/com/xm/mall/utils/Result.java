package com.xm.mall.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 统一返回结果集
 * @author huyan
 * @date 2019-04-09 15:38
 */
@Data
public class Result extends HashMap implements Serializable {

    private Integer code;
    private String msg;

    public Result(){}

//    public Result(ResultCode resultCode){
//        this.code = resultCode.getCode();
//        this.msg = resultCode.getMsg();
//    }


    public Result(ResultCode resultCode) {
        super.put("code", resultCode.getCode());
        super.put("msg", resultCode.getMsg());
    }

    public static Result of(ResultCode resultCode){
        return new Result(resultCode);
    }

    public static Result success(){
        return of(ResultCode.SUCCESS);
    }

    public static Result error(){
        return of(ResultCode.ERROR);
    }


    @Override
    public Object put(Object key, Object value) {
        super.put(key, value);
        return this;
    }
}
