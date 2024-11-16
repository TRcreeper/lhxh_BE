package com.lhxh.demo.service.impl;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhxh.demo.mapper.EquipmentMapper;
import com.lhxh.demo.pojo.Equipment;
import com.lhxh.demo.pojo.Member;
import com.lhxh.demo.pojo.PageBean;
import com.lhxh.demo.pojo.Result;
import com.lhxh.demo.service.EquipmentService;

@Service
public class EquimentServiceImpl implements EquipmentService{

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public void add(Equipment equipment) {
        equipmentMapper.add(equipment);
    }

    @Override
    public PageBean<Equipment> list(Integer pageNum, Integer pageSize) {
        //创建pagebean对象
        PageBean<Equipment> pb=new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Equipment> es=equipmentMapper.list();
        Page<Equipment> p=(Page<Equipment>) es;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void update(Equipment equipment) {
        equipmentMapper.update(equipment);
    }

    @Override
    public void deleteById(Integer id) {
        equipmentMapper.deleteById(id);

    }

}
