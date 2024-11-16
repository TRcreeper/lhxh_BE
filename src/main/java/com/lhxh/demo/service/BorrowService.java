package com.lhxh.demo.service;

import com.lhxh.demo.pojo.Borrow;
import com.lhxh.demo.pojo.PageBean;

public interface BorrowService {

    void add(Borrow borrow);

    PageBean<Borrow> list(Integer pageNum, Integer pageSize, String equipmentName, String memberName);

    void update(Borrow borrow);

}
