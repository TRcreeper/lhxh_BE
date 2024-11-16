package com.lhxh.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhxh.demo.mapper.CategoryMapper;
import com.lhxh.demo.pojo.Category;
import com.lhxh.demo.pojo.Member;
import com.lhxh.demo.service.CategoryService;
import com.lhxh.demo.service.MemberService;
import com.lhxh.demo.utils.ThreadLocalUtil;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private MemberService memberService;

    @Override
    public void add(Category category) {
        //补充属性
        // category.setCreateTime(LocalDateTime.now());
        // category.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId=(Integer) map.get("id");

        //查询会员id
        Member m=memberService.findByUserId(userId);

        category.setCreateMember(m.getId());
        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        // Map<String,Object> map = ThreadLocalUtil.get();
        // Integer userId=(Integer) map.get("id");
        return categoryMapper.list();
    }

    @Override
    public Category findById(Integer id) {
        Category c = categoryMapper.findById(id);
        return c;
    }

    @Override
    public void update(Category category) {
        // category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryMapper.deleteById(id);
    }

}
