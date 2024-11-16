package com.lhxh.demo.service;

import com.lhxh.demo.pojo.Member;
import com.lhxh.demo.pojo.PageBean;

import jakarta.servlet.http.HttpServletResponse;

public interface MemberService {
    //添加会员
    void add(Member member);

    //删除申请同时添加会员
    void deleteApplyAndAddMember(Integer applyId,Member member);

    //查询会员列表
    PageBean<Member> list(Integer pageNum, Integer pageSize, String name);

    //会员更新信息
    void update(Member member);

    //根据userId查询会员
    Member findByUserId(Integer userId);

    //导出数据
    void exportData(HttpServletResponse response);

    //删除会员
    void deleteById(Integer id);

    void updateRole(Integer id, String role);

}
