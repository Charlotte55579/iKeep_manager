package com.fts.fts.fitness_tracking_system.utils;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    // 响应状态码，通常使用 HTTP 状态码，如 200 表示成功，400 表示客户端错误，500 表示服务器错误等
    private int code;
    // 响应消息，对结果的描述信息
    private String message;
    // 响应数据，可以是任意类型的数据
    private T data;

    // 构造函数，用于创建成功的结果
    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 构造函数，用于创建无数据的结果，例如只返回状态码和消息
    public Result(int code, String message) {
        this(code, message, null);
    }

    // 构造函数，用于创建成功但无额外消息的结果，只返回数据
    public Result(T data) {
        this(200, "成功", data);
    }

    // 构造函数，用于创建仅包含状态码的结果，默认消息为"成功"
    public Result(int code) {
        this(code, "成功", null);
    }

    // 获取状态码的方法
    public int getCode() {
        return code;
    }

    // 设置状态码的方法
    public void setCode(int code) {
        this.code = code;
    }

    // 获取消息的方法
    public String getMessage() {
        return message;
    }

    // 设置消息的方法
    public void setMessage(String message) {
        this.message = message;
    }

    // 获取数据的方法
    public T getData() {
        return data;
    }

    // 设置数据的方法
    public void setData(T data) {
        this.data = data;
    }
}
