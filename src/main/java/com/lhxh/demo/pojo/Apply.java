package com.lhxh.demo.pojo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class Apply {
    private Integer id;
    @NotNull
    private Integer userId;
    @NotNull
    private String name;
    @NotEmpty
    @Email
    private String email;
    @NotNull
    private String dormitory;
    @NotNull
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime applyTime;
}
