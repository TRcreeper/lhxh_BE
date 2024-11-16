package com.lhxh.demo.controller;

import java.sql.ResultSet;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhxh.demo.pojo.Borrow;
import com.lhxh.demo.pojo.Member;
import com.lhxh.demo.pojo.PageBean;
import com.lhxh.demo.pojo.Result;
import com.lhxh.demo.pojo.User;
import com.lhxh.demo.service.BorrowService;
import com.lhxh.demo.service.MemberService;
import com.lhxh.demo.utils.ThreadLocalUtil;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;
    @Autowired
    private MemberService memberService;

    @PostMapping("/member")
    public Result add(@RequestBody Borrow borrow){
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer userId=(Integer)map.get("id");
        Member m=memberService.findByUserId(userId);
        if(m==null){
            return Result.error("您还不是会员");
        }
        borrow.setMemberId(m.getId());
        borrowService.add(borrow);
        return Result.success();
    }

    //条件查询借用设备列表
    @GetMapping("/member")
    public Result<PageBean<Borrow>> list(
        Integer pageNum,
        Integer pageSize,
        @RequestParam(required = false) String equipmentName,
        @RequestParam(required = false) String memberName
    )
    {
        PageBean<Borrow> pb = borrowService.list(pageNum,pageSize,equipmentName,memberName);
        return Result.success(pb);
    }

    @PutMapping("/member")
    public Result update(@RequestBody Borrow borrow)
    {
        borrowService.update(borrow);
        return Result.success();
    }
}
