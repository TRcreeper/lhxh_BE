package com.lhxh.demo.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Participate {
    private Integer memberId;
    @NotNull
    private Integer activityId;
    private String name;
    private String title;
}
