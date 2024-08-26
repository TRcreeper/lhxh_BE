package com.lhxh.demo.service;

import com.lhxh.demo.pojo.Member;
import com.lhxh.demo.pojo.PageBean;

public interface MemberService {
    //添加会员
    void add(Member member);

    //查询会员列表
    PageBean<Member> list(Integer pageNum, Integer pageSize, String name);

    //会员更新信息
    void update(Member member);

    //根据userId查询会员
    Member findByUserId(Integer userId);

}
