package com.example.demo.controller;
import com.example.demo.form.RegisterParams;
import com.example.demo.pojo.User;
import com.example.demo.pojo.Result;
import com.example.demo.service.impl.UserService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody @Validated RegisterParams params) {
        //查询用户
        User u = userService.findByUser(params.username);

        if (u != null) {
            return  Result.error("用户已被注册");
        }

        userService.register(params.username, params.password);
        return Result.success();
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

