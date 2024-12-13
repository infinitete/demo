package com.example.demo.service.impl;


import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUser(String user) {
        User u = userMapper.findByUser(user);
        return null;
    }

    @Override
    public void register(String user, String password) {
        userMapper.add(user,password);
    }
}
