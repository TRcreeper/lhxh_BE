package com.lhxh.demo.service;

import com.lhxh.demo.pojo.PageBean;
import com.lhxh.demo.pojo.User;

public interface UserService {
    //根据用户名查询用户
    User findByUserName(String username);
    //注册
    void register(String username, String password);
    //更新
    void update(User user);
    //更新密码
    void updatePwd(String newPwd);
    //根据姓名查询
    PageBean<User> list(Integer pageNum, Integer pageSize, String nickname);

}
