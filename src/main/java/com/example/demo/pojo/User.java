package com.example.demo.pojo;

import lombok.Data;

@Data
public class User {
    private int id;
    private String user;
    private String password;
    private String createtime;
    private String updatetime;
}
