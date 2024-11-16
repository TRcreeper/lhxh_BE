package com.lhxh.demo.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhxh.demo.mapper.UserMapper;
import com.lhxh.demo.pojo.Activity;
import com.lhxh.demo.pojo.Category;
import com.lhxh.demo.pojo.PageBean;
import com.lhxh.demo.pojo.User;
import com.lhxh.demo.service.UserService;
import com.lhxh.demo.utils.Md5Util;
import com.lhxh.demo.utils.ThreadLocalUtil;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        User u=userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        //加密
        String md5String=Md5Util.getMD5String(password);
        //添加
        userMapper.add(username,md5String);
    }

    @Override
    public void update(User user) {
        // user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer id=(Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
        
    }

    @Override
    public PageBean<User> list(Integer pageNum, Integer pageSize, String nickname) {
        //创建PageBean对象
        PageBean<User> pb=new PageBean<>();
        //开启分页查询PageHelper
        PageHelper.startPage(pageNum,pageSize);
        //调用mapper
        // Map<String,Object> map= ThreadLocalUtil.get();
        // Integer userId=(Integer) map.get("id");
        List<User> us = userMapper.list(nickname);
        //Page中提供了方法，获取Pagehelper分页查询后得到的总记录条数和当前页数据
        Page<User> p=(Page<User>) us;
        //把数据填充到pagebean
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());

        return pb;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        //查数据库获取数据
        List<User> us= userMapper.list(null);
        //写入excel
        // 在内存中创建一个Excel文件
        try{
        XSSFWorkbook excel = new XSSFWorkbook();
        // 在Excel文件中创建一个sheet页
        XSSFSheet sheet = excel.createSheet("studentInfo");
        // 在sheet中创建行对象，row从0开始
        XSSFRow row = sheet.createRow(0);
        // 创建单元格并且写入文件内容
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("username");
        row.createCell(2).setCellValue("name");
        row.createCell(3).setCellValue("email");
        row = sheet.createRow(1);
        row.createCell(0).setCellValue("zhangsan");
        row.createCell(1).setCellValue("29");
        row.createCell(2).setCellValue("123456");

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=students.xlsx");
        //通过输出流
        ServletOutputStream out=response.getOutputStream();
        excel.write(out);
        out.close();
        excel.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
