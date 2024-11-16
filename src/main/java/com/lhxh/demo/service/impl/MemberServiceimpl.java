package com.lhxh.demo.service.impl;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhxh.demo.mapper.ApplyMapper;
import com.lhxh.demo.mapper.MemberMapper;
import com.lhxh.demo.pojo.Member;
import com.lhxh.demo.pojo.PageBean;
import com.lhxh.demo.pojo.User;
import com.lhxh.demo.service.MemberService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class MemberServiceimpl implements MemberService{

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private ApplyMapper applyMapper;

    @Override
    public void add(Member member) {
        member.setRole("会员");
        member.setCreateTime(LocalDateTime.now());
        memberMapper.add(member);
    }

    @Transactional
    @Override
    public void deleteApplyAndAddMember(Integer applyId, Member member) {
        // 事务操作
        try {
            member.setRole("会员");
            member.setCreateTime(LocalDateTime.now());
            applyMapper.deleteById(applyId);
            memberMapper.add(member);
        } catch (Exception e) {
            // 如果有异常，事务会回滚
            throw e;
        }
        
    }

    @Override
    public PageBean<Member> list(Integer pageNum, Integer pageSize, String name) {
        //创建pagebean对象
        PageBean<Member> pb=new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Member> ms=memberMapper.list(name);
        Page<Member> p=(Page<Member>) ms;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void update(Member member) {
        memberMapper.update(member);
    }

    @Override
    public Member findByUserId(Integer userId) {
        Member m=memberMapper.findByUserId(userId);
        return m;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        //查数据库获取数据
        List<Member> members= memberMapper.list(null);
        //写入excel
        // 在内存中创建一个Excel文件
        try{
        XSSFWorkbook excel = new XSSFWorkbook();
        // 在Excel文件中创建一个sheet页
        XSSFSheet sheet = excel.createSheet("MemberInfo");
        // 在sheet中创建行对象，row从0开始
        XSSFRow headerRow = sheet.createRow(0);
        // 创建单元格并且写入文件内容
        // row.createCell(0).setCellValue("id");
        // row.createCell(1).setCellValue("username");
        // row.createCell(2).setCellValue("name");
        // row.createCell(3).setCellValue("email");
        // row = sheet.createRow(1);
        // row.createCell(0).setCellValue("zhangsan");
        // row.createCell(1).setCellValue("29");
        // row.createCell(2).setCellValue("123456");

        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("User ID");
        headerRow.createCell(2).setCellValue("Name");
        headerRow.createCell(3).setCellValue("Dormitory");
        headerRow.createCell(4).setCellValue("Email");
        headerRow.createCell(5).setCellValue("Role");
        headerRow.createCell(6).setCellValue("Create Time");
        
        // 填充数据
        int rowIndex = 1;
        for (Member member : members) {
            XSSFRow row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(member.getId());
            row.createCell(1).setCellValue(member.getUserId());
            row.createCell(2).setCellValue(member.getName());
            row.createCell(3).setCellValue(member.getDormitory());
            row.createCell(4).setCellValue(member.getEmail());
            row.createCell(5).setCellValue(member.getRole());
            row.createCell(6).setCellValue(member.getCreateTime() != null ? member.getCreateTime().toString() : "");
        }

        //设置响应头
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

    @Override
    public void deleteById(Integer id) {
        memberMapper.deleteById(id);
    }

    @Override
    public void updateRole(Integer id, String role) {
        memberMapper.updateRole(id,role);
    }




}
