package com.tuuli.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tuuli
 * @since 2023-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tb_questions",autoResultMap = true)
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String description;

    private String picture;

    @TableField(exist = false)
    private MultipartFile file;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private String[] options;

    private String answer;

    @TableField(value = "ques_cour")
    private Integer quesCourId;

    @TableField(exist = false)
    private String quesCourStr;

    private String chapter;

    private String type;

    @TableLogic
    private Integer deleted;

    private String hard;

    private Double score;


}
