package com.lhxh.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.lhxh.demo.pojo.Participate;

@Mapper
public interface ParticipateMapper {

    @Insert("insert into participate(member_id,activity_id) "+
    "values(#{memberId},#{activityId})")
    void add(Participate participate);

    //会员自己退出活动
    @Delete("delete from participate where activity_id=#{activityId} AND member_id=#{memberId}")
    void deleteParticipate(Integer activityId,Integer memberId);

    @Select("select * from participate where activity_id=#{activityId} AND member_id=#{memberId}")
    Participate participateInfo(Integer activityId, Integer memberId);


    //参加活动列表查询
    @Select("select * from participate_list where activity_id=#{activityId}")
    List<Participate> list(Integer activityId);

    
}