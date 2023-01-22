package com.tuuli.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author tuuli
 * @time Created in 2023/1/18 23:09
 * @description 接受前端“添加题目”功能中传过来的参数
 */
@Data
public class QuestionDto {
    String description;
    //List options;
    String options;
    String answer;
    Integer quesCourId;
    String chapter;
    String hard;
    Integer score;
    String type;
    MultipartFile[] file;
}
