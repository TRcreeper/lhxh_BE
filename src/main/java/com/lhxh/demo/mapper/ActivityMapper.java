package com.lhxh.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lhxh.demo.pojo.Activity;

@Mapper
public interface ActivityMapper {

    //新增
    @Insert("insert into activity(title,content,state,category_id,create_user,create_time,update_time) "+
    "values(#{title},#{content},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Activity activity);

    //活动列表
    List<Activity> list(Integer userId, Integer categoryId, String state);

    //更新
    @Update("update activity set title=#{title},content=#{content},state=#{state},category_id=#{categoryId},update_time=#{updateTime} where id=#{id}")
    void update(Activity activity);

    //获取活动详情
    @Select("select * from activity where id=#{id}")
    Activity findById(Integer id);

    //删除活动
    @Delete("delete from activity where id=#{id}")
    void deleteById(Integer id);

}
