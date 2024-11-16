package com.lhxh.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.lhxh.demo.pojo.Apply;

@Mapper
public interface ApplyMapper {

    //新增申请
    @Insert("insert into apply(user_id,name,email,dormitory,content,apply_time) "+
    "values(#{userId},#{name},#{email},#{dormitory},#{content},#{applyTime})")
    void add(Apply apply);

    //根据userId查询申请
    @Select("select * from apply where user_id=#{userId}")
    Apply findByUserId(Integer userId);

    //查询申请列表
    List<Apply> list();

    //删除申请
    @Delete("delete from apply where id=#{id}")
    void deleteById(Integer id);

}
