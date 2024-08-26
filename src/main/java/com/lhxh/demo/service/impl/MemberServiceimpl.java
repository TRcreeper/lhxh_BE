package com.lhxh.demo.service.impl;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhxh.demo.mapper.MemberMapper;
import com.lhxh.demo.pojo.Member;
import com.lhxh.demo.pojo.PageBean;
import com.lhxh.demo.service.MemberService;

@Service
public class MemberServiceimpl implements MemberService{

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public void add(Member member) {
        member.setCreateTime(LocalDateTime.now());
        memberMapper.add(member);
    }

    @Override
    public PageBean<Member> list(Integer pageNum, Integer pageSize, String name) {
        //创建pagebean对象
        PageBean<Member> pb=new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Member> ms=memberMapper.list(name);
        Page<Member> p=(Page<Member>) ms;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void update(Member member) {
        memberMapper.update(member);
    }

    @Override
    public Member findByUserId(Integer userId) {
        Member m=memberMapper.findByUserId(userId);
        return m;
    }


}
