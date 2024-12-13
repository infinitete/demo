package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user WHERE user=#{user}")
    User findByUser(String user);

    @Insert("insert into user(user,password,createtime,updatetime)" + "Values(#{user},#{password},now(),now())")

    void add(String user, String password);
}
