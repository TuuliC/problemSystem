package com.tuuli.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author tuuli
 * @time Created in 2023/2/7 22:52
 * @description
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //关于图片上传后需要重启服务器才能刷新图片
        //这是一种保护机制，为了防止绝对路径被看出来，目录结构暴露
        //解决方法:将虚拟路径/images/ 向绝对路径映射

        File staticDir = null;
        try {
            staticDir = new File(ResourceUtils.getURL("classpath:static").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String path = "D:\\work\\javaidea\\Project\\problemSystem\\src\\main\\resources\\static\\questionsImages\\";

        String realPath = null;
        try {
            realPath = new File("src/main/resources/static/questionsImages").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //方法一，会出现问题，需重启后才能获取资源
        //registry.addResourceHandler("/myImages/**").addResourceLocations("file:"+staticDir.getPath() + "\\questionsImages\\");
        //方法二，不会出现方法一的问题，但是只能从本地获取资源
        //registry.addResourceHandler("/myImages/**").addResourceLocations("file:"+path);
        //方法三，同方法二，区别是动态获取本地地址
        registry.addResourceHandler("/myImages/**").addResourceLocations("file:" + realPath + "/");

    }
}

