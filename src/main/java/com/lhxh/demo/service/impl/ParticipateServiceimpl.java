package com.lhxh.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhxh.demo.mapper.MemberMapper;
import com.lhxh.demo.mapper.ParticipateMapper;
import com.lhxh.demo.pojo.Member;
import com.lhxh.demo.pojo.PageBean;
import com.lhxh.demo.pojo.Participate;
import com.lhxh.demo.pojo.User;
import com.lhxh.demo.service.MemberService;
import com.lhxh.demo.service.ParticipateService;
import com.lhxh.demo.utils.ThreadLocalUtil;

@Service
public class ParticipateServiceimpl implements ParticipateService{

    @Autowired
    private ParticipateMapper participateMapper;
    

    @Override
    public void add(Participate participate) {
        participateMapper.add(participate);
    }


    @Override
    public void deleteParticipate(Integer activityId,Integer memberId) {
        participateMapper.deleteParticipate(activityId,memberId);
    }


    @Override
    public Participate participateInfo(Integer activityId, Integer memberId) {
        Participate participate=participateMapper.participateInfo(activityId,memberId);
        return participate;
    }


    @Override
    public PageBean<Participate> list(Integer pageNum, Integer pageSize, Integer activityId) {
        //创建PageBean对象
        PageBean<Participate> pb=new PageBean<>();
        //开启分页查询PageHelper
        PageHelper.startPage(pageNum,pageSize);
        //调用mapper
        // Map<String,Object> map= ThreadLocalUtil.get();
        // Integer userId=(Integer) map.get("id");
        List<Participate> ps = participateMapper.list(activityId);
        //Page中提供了方法，获取Pagehelper分页查询后得到的总记录条数和当前页数据
        Page<Participate> p=(Page<Participate>) ps;
        //把数据填充到pagebean
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

}
