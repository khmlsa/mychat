package com.tr.pojo;

import lombok.Data;

@Data
public class Result {
    private String msg;
    private Object data;
    private int status;
    public static Result ok(Object data,String msg){
        Result result = new Result();
        result.setStatus(200);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }
    public static Result ok(String msg){
        Result result = new Result();
        result.setStatus(200);
        result.setMsg(msg);
        return result;
    }

    public static Result error(String msg){
        Result result = new Result();
        result.setStatus(400);
        result.setMsg(msg);
        return result;
    }
}
