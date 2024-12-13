package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.pulsar.PulsarAutoConfiguration;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result {
    private int code;
    private String msg;
    private Object data;

    public static <E> Result success(E data) { return new Result(0,"操作成功",data);}

    public static Result success() { return new Result(0,"操作成功",null); }


    public static Result error(String message) { return new Result(1,message,null);}


}
