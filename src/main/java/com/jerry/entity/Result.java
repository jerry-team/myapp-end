package com.jerry.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable
{
    final static int SUCCESS = 100;
    final static int FAILURE = 101;
    final static int NO_PERMISSION = 102;
    private int code;
    private String msg;
    private Object data;
    Result(Integer code, String msg, Object data ){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static Result succ(Object data)
    {
        return succ(200, "操作成功", data);
    }
    public static Result succ(int code, String msg, Object data)
    {
        Result r = new Result(code,msg,data);
//        r.setCode(code);
//        r.setMsg(msg);
//        r.setData(data);
        return r;
    }
    public static Result fail(String msg)
    {
        return fail(400, msg, null);
    }
    public static Result fail(String msg, Object data)
    {
        return fail(400, msg, data);
    }
    public static Result fail(int code, String msg, Object data)
    {
        Result r = new Result(code,msg,data);
//        r.setCode(code);
//        r.setMsg(msg);
//        r.setData(data);
        return r;
    }
    public static Result fail() {
        return new Result(Result.FAILURE, "调用失败", null);
    }
    public static Result permissionDeny()
    {
        return new Result(Result.NO_PERMISSION, "没有权限", null);
    }
}
