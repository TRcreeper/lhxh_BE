package com.lhxh.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhxh.demo.pojo.Activity;
import com.lhxh.demo.pojo.PageBean;
import com.lhxh.demo.pojo.Result;
import com.lhxh.demo.service.ActivityService;
import com.lhxh.demo.utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/activity")
public class ActivityController {
    
    @Autowired
    private ActivityService activityService;

    @PostMapping
    public Result add(@RequestBody @Validated Activity activity)
    {
        activityService.add(activity);
        return Result.success();
    }
    
    @PutMapping
    public Result update(@RequestBody @Validated Activity activity)
    {
        activityService.update(activity);
        return Result.success();
    }

    @GetMapping("/detail")
    public Result<Activity> detail(Integer id)
    {
        Activity a = activityService.findById(id);
        return Result.success(a);
    }

    @GetMapping
    public Result<PageBean<Activity>> list(
        Integer pageNum,
        Integer pageSize,
        @RequestParam(required = false) Integer categoryId,
        @RequestParam(required = false) String state
    )
    {
        PageBean<Activity> pb = activityService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }

    @DeleteMapping
    public Result delete(Integer id)
    {
        activityService.deleteById(id);
        return Result.success();
    }
    
}
