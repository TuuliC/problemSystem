package com.tuuli.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tuuli
 * @time Created in 2023/1/18 23:09
 * @description 接受前端“添加题目”功能中传过来的参数
 */
@Data
public class QuestionDto {
    String description;
    String[] options;
    String answer;
    Integer quesCourId;
    String chapter;
    String hard;
    Double score;
    String type;
    MultipartFile file;
}
