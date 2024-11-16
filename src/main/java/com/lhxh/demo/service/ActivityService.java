package com.lhxh.demo.service;

import com.lhxh.demo.pojo.Activity;
import com.lhxh.demo.pojo.PageBean;

public interface ActivityService {

    //新增活动
    void add(Activity activity);
    //条件分页列表查询
    PageBean<Activity> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
    //更新
    void update(Activity activity);
    //获取活动详情
    Activity findById(Integer id);
    //删除活动
    void deleteById(Integer id);
    PageBean<Activity> draftList(Integer pageNum, Integer pageSize, Integer categoryId);

}
