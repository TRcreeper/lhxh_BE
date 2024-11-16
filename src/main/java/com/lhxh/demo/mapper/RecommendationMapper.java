package com.lhxh.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.lhxh.demo.pojo.Recommendation;

@Mapper
public interface RecommendationMapper {
    //新增
    @Insert("insert into recommendation(title,content,create_user,create_time) "+
    "values(#{title},#{content},#{createUser},#{createTime})")
    void add(Recommendation recommendation);

    @Select("select * from recommendation")
    List<Recommendation> list();
    
    //删除
    @Delete("delete from recommendation where id=#{id}")
    void deleteById(Integer id);

}
