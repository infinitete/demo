package com.example.demo.service.impl;

import com.example.demo.pojo.User;

public interface UserService {
    User findByUser(String user);

    void register(String user, String password);
}
