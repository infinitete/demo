package com.example.demo.controller;
import com.example.demo.form.RegisterParams;
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
    public Result register(RegisterParams params) {
        //查询用户
        User u = userService.findByUser(params.username);

        if (u != null) {
            return  Result.error("用户已被注册");
        }

        // 判断请求参数中的用户名和密码是否符合规则
        if (params.username.trim().length() < 6) {
            return  Result.error("用户名不能包含空格且长度不应小于6个字符");
        }

        if (params.password.trim().length() < 8) {
            return  Result.error("密码不能包含空格且长度不应小于8个字符");
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

