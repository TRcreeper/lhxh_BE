package com.lhxh.demo.service;

import com.lhxh.demo.pojo.Apply;
import com.lhxh.demo.pojo.PageBean;

public interface ApplyService {

    //添加申请
    void add(Apply apply);

    //根据用户id查询申请
    Apply findByUserId(Integer id);

    //查询申请列表
    PageBean<Apply> list(Integer pageNum, Integer pageSize);

    //删除申请
    void deleteById(Integer id);

}
