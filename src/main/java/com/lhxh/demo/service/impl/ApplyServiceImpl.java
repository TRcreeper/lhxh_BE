package com.lhxh.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhxh.demo.mapper.ApplyMapper;
import com.lhxh.demo.pojo.Apply;
import com.lhxh.demo.pojo.PageBean;
import com.lhxh.demo.pojo.User;
import com.lhxh.demo.service.ApplyService;
import com.lhxh.demo.utils.ThreadLocalUtil;

@Service
public class ApplyServiceImpl implements ApplyService{

    @Autowired
    private ApplyMapper applyMapper;

    @Override
    public void add(Apply apply) {
        apply.setApplyTime(LocalDateTime.now());
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId=(Integer) map.get("id");
        apply.setUserId(userId);
        applyMapper.add(apply);
    }

    @Override
    public Apply findByUserId(Integer userId) {
        Apply a=applyMapper.findByUserId(userId);
        return a;
    }

    @Override
    public PageBean<Apply> list(Integer pageNum, Integer pageSize) {
        //创建PageBean对象
        PageBean<Apply> pb=new PageBean<>();
        //开启分页查询PageHelper
        PageHelper.startPage(pageNum,pageSize);
        //调用mapper
        // Map<String,Object> map= ThreadLocalUtil.get();
        // Integer userId=(Integer) map.get("id");
        List<Apply> as = applyMapper.list();
        //Page中提供了方法，获取Pagehelper分页查询后得到的总记录条数和当前页数据
        Page<Apply> p=(Page<Apply>) as;
        //把数据填充到pagebean
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());

        return pb;
    }

    @Override
    public void deleteById(Integer id) {
        applyMapper.deleteById(id);
    }



}
