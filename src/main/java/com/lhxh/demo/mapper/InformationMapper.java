package com.lhxh.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lhxh.demo.pojo.Information;

@Mapper
public interface InformationMapper {

    //更新
    @Update("update information set title=#{title},content=#{content},release_time=#{releaseTime},create_member=#{createMember} where id=#{id}")
    void update(Information information);

    @Select("select * from information where id=1")
    Information information();

}
