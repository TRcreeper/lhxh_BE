package com.lhxh.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lhxh.demo.pojo.Member;

@Mapper
public interface MemberMapper {

    //新增会员
    @Insert("insert into member(user_id,name,email,dormitory,role,create_time) "+
    "values(#{userId},#{name},#{email},#{dormitory},#{role},#{createTime})")
    void add(Member member);

    //根据名字查询
    List<Member> list(String name);

    //会员更改信息
    @Update("update member set name=#{name},email=#{email},dormitory=#{dormitory} where id=#{id}")
    void update(Member member);

    //根据userId查询会员
    @Select("select * from member where user_id=#{userId}")
    Member findByUserId(Integer userId);

    //删除会员
    @Delete("delete from member where id=#{id}")
    void deleteById(Integer id);

    @Update("update member set role=#{role} where id=#{id}")
    void updateRole(Integer id, String role);

}
