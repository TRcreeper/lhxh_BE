package com.lhxh.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// import com.lhxh.demo.pojo.Activity;
import com.lhxh.demo.pojo.PageBean;
import com.lhxh.demo.pojo.Recommendation;
import com.lhxh.demo.pojo.Result;
import com.lhxh.demo.service.RecommendationService;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @PostMapping
    public Result add(@RequestBody Recommendation recommendation){
        recommendationService.add(recommendation);
        return Result.success();
    }

    @GetMapping("/worker")
    public Result<PageBean<Recommendation>> list(
        Integer pageNum,
        Integer pageSize
    )
    {
        PageBean<Recommendation> pb = recommendationService.list(pageNum,pageSize);
        return Result.success(pb);
    }

    @DeleteMapping("/worker")
    public Result delete(Integer id)
    {
        recommendationService.deleteById(id);
        return Result.success();
    }
}
