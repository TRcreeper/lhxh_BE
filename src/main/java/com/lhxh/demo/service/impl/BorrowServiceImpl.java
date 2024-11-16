package com.lhxh.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhxh.demo.mapper.BorrowMapper;
import com.lhxh.demo.pojo.Borrow;
import com.lhxh.demo.pojo.Member;
import com.lhxh.demo.pojo.PageBean;
import com.lhxh.demo.service.BorrowService;

@Service
public class BorrowServiceImpl implements BorrowService{

    @Autowired
    private BorrowMapper borrowMapper;

    @Override
    public void add(Borrow borrow) {
        borrow.setState("借用中");
        borrow.setBorrowTime(LocalDateTime.now());
        borrowMapper.add(borrow);
    }

    @Override
    public PageBean<Borrow> list(Integer pageNum, Integer pageSize, String equipmentName, String memberName) {
        //创建pagebean对象
        PageBean<Borrow> pb=new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        borrowMapper.callUpdateBorrowingStatus();
        List<Borrow> bs=borrowMapper.list(equipmentName,memberName);
        Page<Borrow> p=(Page<Borrow>) bs;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void update(Borrow borrow) {
        borrow.setState("已归还");
        borrowMapper.update(borrow);
    }

}
