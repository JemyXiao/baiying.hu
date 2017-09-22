package com.baiying.hu.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 响应前端接口类
 */
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ResultModel {
    private int code;
    private String message;
    private Integer total;
    private Object data;

    public ResultModel(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    private Object error;

    public static final ResultModel SUCCESS = new ResultModel(200, "success");

    public ResultModel() {
    }

    public ResultModel(int code, String message, Object data, Integer total) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.total = total;
    }

    public ResultModel(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultModel(int code) {
        this.code = code;
    }

    public ResultModel(String message) {
        this.code = code;
        this.message = message;
    }
    public ResultModel(String message, Object data, int total) {
        this.code = 200;
        this.message = message;
        this.data = data;
        this.total = total;
    }

    public ResultModel(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}

