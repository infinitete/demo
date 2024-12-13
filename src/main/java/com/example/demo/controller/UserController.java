package com.example.demo.controller;
import com.example.demo.pojo.User;
import com.example.demo.pojo.Result;
import com.example.demo.service.impl.UserService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String user, String password) {
        //查询用户
        User u = userService.findByUser(user);
        if (user != null && user.length() >= 5 && user.length() <= 16 && password != null && password.length() >= 5 && password.length() <= 16) {
            if (u == null) {
                //没有被占用
                //注册
                userService.register(user, password);
                return Result.success();
            } else {
                //占用
                return Result.error("用户名被占用");
            }

        }
        else{
            return Result.error("参数不合法");
        }
    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^.${5,16}$") String user,@Pattern(regexp = "^.${5,16}$") String password){
        //根据用户名查询用户
        User loingUser = userService.findByUser(user);
        //判断用户是否存在
        if(loingUser == null){
            return Result.error("用户名错误");
        }

        //判断密码是否正确
        if(loingUser.getPassword().equals(password)){
            return Result.success("jwt token令牌");
        }
        return Result.error("密码错误");
    }
}

