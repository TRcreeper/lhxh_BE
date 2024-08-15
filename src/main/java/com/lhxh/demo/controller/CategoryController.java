package com.lhxh.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.lhxh.demo.pojo.Category;
import com.lhxh.demo.pojo.Result;
import com.lhxh.demo.service.CategoryService;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category)
    {
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping
    public Result<List<Category>> list()
    {
        List<Category> cs=categoryService.list();
        return Result.success(cs);
    }

    @GetMapping("/detail")
    public Result<Category> detail(Integer id)
    {
        Category c= categoryService.findById(id);
        return Result.success(c);
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category)
    {
        categoryService.update(category);
        return Result.success();        
    }

    @DeleteMapping
    public Result delete(Integer id)
    {
        categoryService.deleteById(id);
        return Result.success();
    }
}
