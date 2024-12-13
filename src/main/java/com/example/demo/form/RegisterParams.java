package com.example.demo.form;

import jakarta.validation.constraints.Pattern;

public class RegisterParams {

    @Pattern(regexp = "^[a-zA-Z]{6,12}$", message = "用户名是6-12位的大小写")
    public String username;

    @Pattern(regexp = "^[a-zA-Z]{8,16}$", message = "密码是8-16位的大小写")
    public String password;
}
