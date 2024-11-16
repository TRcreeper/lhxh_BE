package com.lhxh.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhxh.demo.mapper.RecommendationMapper;
import com.lhxh.demo.pojo.Activity;
import com.lhxh.demo.pojo.PageBean;
import com.lhxh.demo.pojo.Recommendation;
import com.lhxh.demo.service.RecommendationService;
import com.lhxh.demo.utils.ThreadLocalUtil;

@Service
public class RecommendationServiceImpl implements RecommendationService{

    @Autowired
    private RecommendationMapper recommendationmMapper;

    @Override
    public void add(Recommendation recommendation) {
        recommendation.setCreateTime(LocalDateTime.now());

        //获取用户id
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId=(Integer) map.get("id");
        recommendation.setCreateUser(userId);

        recommendationmMapper.add(recommendation);

    }

    @Override
    public PageBean<Recommendation> list(Integer pageNum, Integer pageSize) {
        //创建PageBean对象
        PageBean<Recommendation> pb=new PageBean<>();
        //开启分页查询PageHelper
        PageHelper.startPage(pageNum,pageSize);
        

        //调用mapper
        // Map<String,Object> map= ThreadLocalUtil.get();
        // Integer userId=(Integer) map.get("id");
        List<Recommendation> as = recommendationmMapper.list();
        //Page中提供了方法，获取Pagehelper分页查询后得到的总记录条数和当前页数据
        Page<Recommendation> p=(Page<Recommendation>) as;
        //把数据填充到pagebean
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());

        return pb;
    }

    @Override
    public void deleteById(Integer id) {
        recommendationmMapper.deleteById(id);
    }

}
