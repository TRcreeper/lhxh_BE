package com.lhxh.demo.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhxh.demo.config.CaptureConfig;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.captcha.generator.RandomGenerator;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping
public class CaptureController {
    @GetMapping("/captcha")
    public void createCaptcha(@RequestParam String key, HttpServletResponse response) throws IOException {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(150, 40);
        // 自定义验证码内容为四则运算方式
        // lineCaptcha.setGenerator(new MathGenerator());

        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);

        lineCaptcha.setGenerator(randomGenerator);
        String code = lineCaptcha.getCode();

        // 把验证码在后端存一份，MAP结构中
        CaptureConfig.captureMap.put(key, code.toLowerCase());

        response.setContentType("image/png");

        lineCaptcha.write(response.getOutputStream());
        // 关闭流
        response.getOutputStream().close();
    }

}