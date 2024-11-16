package com.lhxh.demo.controller;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhxh.demo.pojo.Apply;
import com.lhxh.demo.pojo.Member;
import com.lhxh.demo.pojo.PageBean;
import com.lhxh.demo.pojo.Result;
import com.lhxh.demo.pojo.User;
import com.lhxh.demo.service.ApplyService;
import com.lhxh.demo.service.MemberService;
import com.lhxh.demo.service.UserService;
import com.lhxh.demo.utils.ThreadLocalUtil;

@RestController
@RequestMapping("/apply")
public class ApplyController {
    @Autowired
    private ApplyService applyService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private UserService userService;

    @PostMapping
    public Result add(@RequestBody Apply apply){
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId=(Integer) map.get("id");
        Member m=memberService.findByUserId(userId);
        Apply a=applyService.findByUserId(userId);
        if(m==null&&a==null){
            applyService.add(apply);
            return Result.success();
        }else{
            return Result.error("该用户已注册会员或提交申请");
        }
    }

    @GetMapping
    public Result<Apply> applyInfo(){
        Map<String,Object> map=ThreadLocalUtil.get();
        String username=(String)map.get("username");
        User user=userService.findByUserName(username);
        Apply a=applyService.findByUserId(user.getId());
        return Result.success(a);
    }

    //查询申请列表
    @GetMapping("/list/worker")
    public Result<PageBean<Apply>> list(
        Integer pageNum,
        Integer pageSize
        // @RequestParam(required = false) String nickname
    )
    {
        PageBean<Apply> pb = applyService.list(pageNum,pageSize);
        return Result.success(pb);
    }

    @DeleteMapping
    public Result delete(Integer id)
    {
        applyService.deleteById(id);
        return Result.success();
    }

    


}
