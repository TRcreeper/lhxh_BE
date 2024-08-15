package com.lhxh.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhxh.demo.mapper.ActivityMapper;
import com.lhxh.demo.pojo.Activity;
import com.lhxh.demo.pojo.PageBean;
import com.lhxh.demo.service.ActivityService;
import com.lhxh.demo.utils.ThreadLocalUtil;

@Service
public class ActivitiServiceimpl implements ActivityService{

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public void add(Activity activity) {
        //补充属性
        activity.setCreateTime(LocalDateTime.now());
        activity.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId=(Integer) map.get("id");
        activity.setCreateUser(userId);
        activityMapper.add(activity);
    }

    @Override
    public PageBean<Activity> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //创建PageBean对象
        PageBean<Activity> pb=new PageBean<>();
        //开启分页查询PageHelper
        PageHelper.startPage(pageNum,pageSize);
        //调用mapper
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId=(Integer) map.get("id");
        List<Activity> as = activityMapper.list(userId,categoryId,state);
        //Page中提供了方法，获取Pagehelper分页查询后得到的总记录条数和当前页数据
        Page<Activity> p=(Page<Activity>) as;
        //把数据填充到pagebean
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());

        return pb;
    }

    @Override
    public void update(Activity activity) {
        activity.setUpdateTime(LocalDateTime.now());
        activityMapper.update(activity);
    }

    @Override
    public Activity findById(Integer id) {
        Activity a=activityMapper.findById(id);
        return a;
    }

    @Override
    public void deleteById(Integer id) {
        activityMapper.deleteById(id);
    }

}
