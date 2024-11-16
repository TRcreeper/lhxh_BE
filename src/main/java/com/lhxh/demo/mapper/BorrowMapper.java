package com.lhxh.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.lhxh.demo.pojo.Borrow;

@Mapper
public interface BorrowMapper {

    //添加借用记录
    @Insert("insert into borrow(member_id,equipment_id,borrow_time,duration,state) "+
    "values(#{memberId},#{equipmentId},#{borrowTime},#{duration},#{state})")
    void add(Borrow borrow);

    List<Borrow> list(String equipmentName, String memberName);

    void callUpdateBorrowingStatus();

    @Update("UPDATE borrow SET state = #{state} WHERE member_id = #{memberId} AND equipment_id = #{equipmentId}")
    void update(Borrow borrow);

}
