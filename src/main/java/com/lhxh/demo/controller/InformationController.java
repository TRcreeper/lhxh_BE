package com.lhxh.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhxh.demo.pojo.Information;
import com.lhxh.demo.pojo.Result;
import com.lhxh.demo.service.InformationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/information")
public class InformationController {

    @Autowired
    private InformationService informationService;

    @PutMapping("/worker")
    public Result update(@RequestBody Information information)
    {
        informationService.update(information);
        return Result.success();
    }

    @GetMapping()
    public Result<Information> information()
    {   
        Information i=informationService.information();
        return Result.success(i);

    }
    

}
