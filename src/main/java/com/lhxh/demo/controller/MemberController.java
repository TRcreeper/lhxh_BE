package com.lhxh.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhxh.demo.pojo.Member;
import com.lhxh.demo.pojo.PageBean;
import com.lhxh.demo.pojo.Result;
import com.lhxh.demo.pojo.User;
import com.lhxh.demo.service.MemberService;
import com.lhxh.demo.service.UserService;
import com.lhxh.demo.utils.ThreadLocalUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/member")

public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private UserService userService;

    // //添加会员
    // @PostMapping
    // public Result add(@RequestBody Member member){
    //     memberService.add(member);
    //     return Result.success();
    // }

    @PostMapping("/worker")
    public Result deleteApplyAndAddMember(@RequestParam Integer applyId, @RequestBody Member member){
        memberService.deleteApplyAndAddMember(applyId,member);
        return Result.success();
    }

    //条件查询会员列表
    @GetMapping
    public Result<PageBean<Member>> list(
        Integer pageNum,
        Integer pageSize,
        @RequestParam(required = false) String name
    )
    {
        PageBean<Member> pb = memberService.list(pageNum,pageSize,name);
        return Result.success(pb);
    }

    //根据userId查询会员
    @GetMapping("/memberInfo")
    public Result<Member> memberInfo(){
        Map<String,Object> map=ThreadLocalUtil.get();
        String username=(String)map.get("username");
        User user=userService.findByUserName(username);
        Member m=memberService.findByUserId(user.getId());
        return Result.success(m);
    }


    //普通用户修改接口
    @PutMapping()
    public Result update(@RequestBody @Validated Member member)
    {
        //获取当前用户的会员ID
        Map<String,Object> map=ThreadLocalUtil.get();
        String username=(String)map.get("username");
        User user=userService.findByUserName(username);
        Member m=memberService.findByUserId(user.getId());

        member.setId(m.getId());

        memberService.update(member);
        return Result.success();
    }

        //导出数据
    @GetMapping("/export/worker")
    public void export(HttpServletResponse response){
        memberService.exportData(response);
    }

    @DeleteMapping("/president")
    public Result delete(Integer id)
    {
        memberService.deleteById(id);
        return Result.success();
    }

    @PatchMapping("/president")
    public Result updateRole(@RequestBody Map<String,String> params)
    {
        Integer id=Integer.parseInt(params.get("id"));
        String role=params.get("role");
        memberService.updateRole(id,role);
        return Result.success();
    }
    
}
