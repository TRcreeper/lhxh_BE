package com.lhxh.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.lhxh.demo.pojo.Category;

@Mapper
public interface CategoryMapper {
    //新增
    @Insert("insert into category(category_name,category_alias,create_member) "+
    "values(#{categoryName},#{categoryAlias},#{createMember})")
    void add(Category category);

    //查询所有
    @Select("select * from category")
    List<Category> list();

    //根据id查询
    @Select("select * from category where id=#{id}")
    Category findById(Integer id);

    //更新
    @Select("update category set category_name=#{categoryName},category_alias=#{categoryAlias} where id=#{id}")
    void update(Category category);

    //删除
    @Delete("delete from category where id=#{id}")
    void deleteById(Integer id);

}
