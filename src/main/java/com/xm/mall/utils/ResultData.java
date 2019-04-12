package com.xm.mall.utils;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * 统一返回带数据的结果集
 * @author huyan
 * @date 2019-04-09 15:38
 */
@Data
public class ResultData extends Result implements Serializable {

    private Object data;

    public ResultData(){

    }

    public ResultData(ResultCode resultCode,Object data){
        super(resultCode);
        this.data = data;
    }

    public static Result success(Object data){
        return  new ResultData(ResultCode.SUCCESS,data);
    }

}
