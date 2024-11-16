package com.lhxh.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lhxh.demo.pojo.Equipment;

@Mapper
public interface EquipmentMapper {

    @Insert("insert into equipment(id,member_id,name,description) "+
    "values(#{id},#{memberId},#{name},#{description})")
    void add(Equipment equipment);

    @Select("select * from equipment_list")
    List<Equipment> list();

    @Update("update equipment set name=#{name},description=#{description} where id=#{id} and member_id=#{memberId}")
    void update(Equipment equipment);

    @Delete("delete from equipment where id=#{id}")
    void deleteById(Integer id);

}
