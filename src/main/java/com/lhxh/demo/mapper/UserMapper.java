package com.lhxh.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lhxh.demo.pojo.User;

@Mapper
public interface UserMapper {
    //根据用户名查询用户
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);
    
    //添加
    @Insert("insert into user(username,password,create_time,update_time)"+
    " values(#{username},#{password},now(),now())")
    void add(String username, String password);
    
    //更新
    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void update(User user);

    //重置密码
    @Update("update user set password=#{md5String},update_time=now() where id=#{id}")
    void updatePwd(String md5String, Integer id);

    //根据名字查询
    List<User> list(String nickname);

}
