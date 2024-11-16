package com.lhxh.demo.service;

import com.lhxh.demo.pojo.Equipment;
import com.lhxh.demo.pojo.PageBean;

public interface EquipmentService {

    //添加设备
    void add(Equipment equipment);

    //查询设备列表
    PageBean<Equipment> list(Integer pageNum, Integer pageSize);

    void update(Equipment equipment);


    void deleteById(Integer id);

}
