package com.lhxh.demo.controller;

import com.lhxh.demo.config.CaptureConfig;
import com.lhxh.demo.pojo.Activity;
import com.lhxh.demo.pojo.Member;
import com.lhxh.demo.pojo.PageBean;
import com.lhxh.demo.pojo.Result;
import com.lhxh.demo.service.UserService;
import com.lhxh.demo.service.MemberService;
import com.lhxh.demo.utils.JwtUtil;
import com.lhxh.demo.utils.Md5Util;
import com.lhxh.demo.utils.ThreadLocalUtil;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Pattern;

import com.lhxh.demo.pojo.User;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MemberService memberService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        //查询
        User u=userService.findByUserName(username);
        if(u==null)
        {
            //没有占用，注册
            userService.register(username,password);
            return Result.success();
        } else{
            //占用
            return Result.error("用户名已存在");
        }
        
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password,String captcha,@RequestParam String key)
    {
        //根据用户名查询用户
        User loginUser=userService.findByUserName(username);

        if(loginUser==null){
            return Result.error("用户名错误");
        }

        //验证码是否正确
        if(!captcha.equals(CaptureConfig.captureMap.get(key)))
        {
            CaptureConfig.captureMap.remove(key);
            return Result.error("验证码不正确");
        }


        if(Md5Util.getMD5String(password).equals(loginUser.getPassword()))
        {
            Member m=memberService.findByUserId(loginUser.getId());
            Integer rolelevel=1;
            if(m==null){
                rolelevel=1;
            }else{
                if("会员".equals(m.getRole())){
                    rolelevel=2;
                }else if("干事".equals(m.getRole())){
                    rolelevel=3;
                }else if("社长".equals(m.getRole())){
                    rolelevel=4;
                }
            }
            Map<String,Object> claims=new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            claims.put("rolelevel", rolelevel);
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }

        return Result.error("密码错误");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(/* @RequestHeader(name = "Authorization") String token */){
        /* Map<String,Object> map= JwtUtil.parseToken(token);
        String username=(String)map.get("username"); */
        Map<String,Object> map=ThreadLocalUtil.get();
        String username=(String)map.get("username");
        User user=userService.findByUserName(username);
        return Result.success(user);

    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user)
    {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params)
    {
        //校验参数
        String oldPwd=params.get("old_pwd");
        String newPwd=params.get("new_pwd");
        String rePwd=params.get("re_pwd");

        if(!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(newPwd)||!StringUtils.hasLength(rePwd))
        {
            return Result.error("缺少参数");
        }
        //检验原密码是否正确
        Map<String,Object> map= ThreadLocalUtil.get();
        String username=(String) map.get("username");
        User loginUser = userService.findByUserName(username);
        if(!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd)))
        {
            return Result.error("原密码不正确");
        }
        //校验new和re是否一致
        if(!rePwd.equals(newPwd))
        {
            return Result.error("两次填写密码不一致");
        }

        //调用service完成更新
        userService.updatePwd(newPwd);
        return Result.success();
    }


    //条件查询学生列表
    @GetMapping
    public Result<PageBean<User>> list(
        Integer pageNum,
        Integer pageSize,
        @RequestParam(required = false) String nickname
    )
    {
        PageBean<User> pb = userService.list(pageNum,pageSize,nickname);
        return Result.success(pb);
    }
    
    //导出数据
    @GetMapping("/export/worker")
    public void export(HttpServletResponse response){
        userService.exportData(response);
    }
}
