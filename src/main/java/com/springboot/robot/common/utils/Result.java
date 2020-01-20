package com.springboot.robot.common.utils;

import java.util.HashMap;

/**
 * @author Connor
 * 返回值封装
 */
public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public Result () {
    }

    public static Result ok(Object body) {
        Result r = new Result();
        r.put("status", 200);
        r.put("msg", "操作成功");
        r.put("body", body);
        return r;
    }

    /**
     * 简单操作返回格式
     *
     * @return
     */
    public static Result ok() {
        Result r = new Result();
        r.put("status", 200);
        r.put("msg", "操作成功");
        //r.put("body", r);
        return r;
    }

    //成功自定义返回msg
//    public static Result ok(String msg) {
//        Result r = new Result();
//        r.put("status", 200);
//        r.put("msg", msg);
//        Result result = new Result();
//        r.put("body", result);
//        return r;
//    }


    public static Result failAtClient(String msg) {
        Result response = new Result();
        response.put("status", 400);
        response.put("msg", msg);
        return response;
    }

    public static Result failAtServer(String msg) {
        Result response = new Result();
        response.put("status", 500);
        response.put("msg", msg);
        return response;
    }

    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
