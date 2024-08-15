package com.lhxh.demo.service.impl;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhxh.demo.mapper.UserMapper;
import com.lhxh.demo.pojo.User;
import com.lhxh.demo.service.UserService;
import com.lhxh.demo.utils.Md5Util;
import com.lhxh.demo.utils.ThreadLocalUtil;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        User u=userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        //加密
        String md5String=Md5Util.getMD5String(password);
        //添加
        userMapper.add(username,md5String);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer id=(Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
        
    }

}
