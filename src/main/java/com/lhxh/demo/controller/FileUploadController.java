package com.lhxh.demo.controller;


import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lhxh.demo.pojo.Result;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException
    {
        String originalFilename=file.getOriginalFilename();
        //保证文件名唯一
        String filename=UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf('.'));
        file.transferTo(new File("C:\\Users\\admin\\Desktop\\本地图片\\"+filename));
        return Result.success("url地址。。。");
    }
}
