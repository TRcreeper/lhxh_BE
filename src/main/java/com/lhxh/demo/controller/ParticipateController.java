package com.lhxh.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhxh.demo.mapper.MemberMapper;
import com.lhxh.demo.pojo.Apply;
import com.lhxh.demo.pojo.Member;
import com.lhxh.demo.pojo.PageBean;
import com.lhxh.demo.pojo.Participate;
import com.lhxh.demo.pojo.Result;
import com.lhxh.demo.service.MemberService;
import com.lhxh.demo.service.ParticipateService;
import com.lhxh.demo.utils.ThreadLocalUtil;

@RestController
@RequestMapping("/participate")
public class ParticipateController {

    @Autowired
    private ParticipateService participateService;
    @Autowired
    private MemberService memberService;

    @PostMapping("/member")
    public Result add(@RequestBody @Validated Participate participate){
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer userId=(Integer)map.get("id");
        Member m=memberService.findByUserId(userId);
        if(m==null){
            return Result.error("您还不是会员");
        }
        participate.setMemberId(m.getId());
        participateService.add(participate);
        return Result.success();
    }

    @DeleteMapping("/member")
    public Result delete(Integer activityId){
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer userId=(Integer)map.get("id");
        Member m=memberService.findByUserId(userId);
        if(m==null){
            return Result.error("您还不是会员");
        }

        participateService.deleteParticipate(activityId,m.getId());
        return Result.success();
    }

    @DeleteMapping("/admin/member")
    public Result adminDelete(Integer activityId,Integer memberId){
        participateService.deleteParticipate(activityId,memberId);
        return Result.success();
    }

    @GetMapping()
    public Result<Participate> participateInfo(Integer activityId){
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer userId=(Integer)map.get("id");
        Member m=memberService.findByUserId(userId);
        if(m==null){
            return Result.error("您还不是会员");
        }
        Participate participate=participateService.participateInfo(activityId,m.getId());
        return Result.success(participate);
    }

    //查询参加列表
    @GetMapping("/list/member")
    public Result<PageBean<Participate>> list(
        Integer pageNum,
        Integer pageSize,
        @RequestParam(required = true) Integer activityId
    )
    {
        PageBean<Participate> pb = participateService.list(pageNum,pageSize,activityId);
        return Result.success(pb);
    }
    


}
