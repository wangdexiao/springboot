package com.wadexi.springboot.web.bean;

import lombok.Data;

@Data
public class Result<R> {

    private boolean success = false;
    private String message;
    private R result;

    private Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    private Result(boolean success, String message,R result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public static <R> Result<R> successedResult(R result){
        return new Result<R>(true, "200 ok", result);
    }

    public static <R> Result<R> failResult(String errMsg){
        return new Result<R>(false, errMsg);
    }
}
