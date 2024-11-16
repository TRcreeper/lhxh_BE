package com.lhxh.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Map;

import com.lhxh.demo.mapper.InformationMapper;
import com.lhxh.demo.mapper.MemberMapper;
import com.lhxh.demo.pojo.Information;
import com.lhxh.demo.pojo.Member;
import com.lhxh.demo.service.InformationService;
import com.lhxh.demo.service.MemberService;
import com.lhxh.demo.utils.ThreadLocalUtil;

@Service
public class InformationServiceImpl implements InformationService{

    @Autowired
    private InformationMapper informationMapper;
    @Autowired
    private MemberService memberService;

    @Override
    public void update(Information information) {
        information.setReleaseTime(LocalDateTime.now());
        information.setId(1);

        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId=(Integer) map.get("id");
        //查询会员id
        Member m=memberService.findByUserId(userId);

        information.setCreateMember(m.getId());

        informationMapper.update(information);
    }

    @Override
    public Information information() {
        Information i=informationMapper.information();
        return i;
    }

}
