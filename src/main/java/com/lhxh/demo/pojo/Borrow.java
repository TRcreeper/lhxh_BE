package com.lhxh.demo.pojo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Borrow {
    private Integer memberId;
    private Integer equipmentId;
    private LocalDateTime borrowTime;
    private Integer duration;
    private String state;
    private String equipmentName;
    private String memberName;
}
