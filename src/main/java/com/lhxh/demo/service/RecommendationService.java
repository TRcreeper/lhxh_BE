package com.lhxh.demo.service;

import com.lhxh.demo.pojo.PageBean;
import com.lhxh.demo.pojo.Recommendation;

public interface RecommendationService {

    void add(Recommendation recommendation);

    PageBean<Recommendation> list(Integer pageNum, Integer pageSize);

    void deleteById(Integer id);

}
