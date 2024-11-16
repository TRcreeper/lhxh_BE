package com.lhxh.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhxh.demo.pojo.Equipment;
import com.lhxh.demo.pojo.Member;
import com.lhxh.demo.pojo.PageBean;
import com.lhxh.demo.pojo.Result;
import com.lhxh.demo.service.EquipmentService;
import com.lhxh.demo.service.MemberService;
import com.lhxh.demo.utils.ThreadLocalUtil;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private MemberService memberService;

    @PostMapping("/member")
    public Result add(@RequestBody Equipment equipment){
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId=(Integer) map.get("id");
        Member m=memberService.findByUserId(userId);
        if(m==null){
            return Result.error("您还不是会员");
        }
        equipment.setMemberId(m.getId());
        equipmentService.add(equipment);
        return Result.success();

    }

    @GetMapping("/member")
    public Result<PageBean<Equipment>> list(
        Integer pageNum,
        Integer pageSize
        // @RequestParam(required = false) String name
    )
    {
        PageBean<Equipment> pb = equipmentService.list(pageNum,pageSize);
        return Result.success(pb);
    }

    @PutMapping("/member")
    public Result update(@RequestBody Equipment equipment)
    {
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId=(Integer) map.get("id");
        Member m=memberService.findByUserId(userId);
        if(m==null){
            return Result.error("您还不是会员");
        }
        equipmentService.update(equipment);
        return Result.success();
    }

    @DeleteMapping("/member")
    public Result delete(Integer id)
    {
        equipmentService.deleteById(id);
        return Result.success();
    }
}
