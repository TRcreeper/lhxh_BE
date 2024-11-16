package com.lhxh.demo.service;

import com.lhxh.demo.pojo.PageBean;
import com.lhxh.demo.pojo.Participate;

public interface ParticipateService {

    //添加参加活动记录
    void add(Participate participate);

    //会员自己退出活动
    void deleteParticipate(Integer activityId,Integer memberId);

    //个人查询参加的活动
    Participate participateInfo(Integer activityId, Integer memberId);

    //活动参与人员列表查询
    PageBean<Participate> list(Integer pageNum, Integer pageSize, Integer activityId);

}
