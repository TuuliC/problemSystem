package com.tuuli.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("tb_questions")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String describe;

    private String picture;

    private String option;

    private String answer;
    @TableField(value = "ques_cour")
    private Integer quesCour;

    private String chapter;

    @TableLogic
    private Integer delete;

    private Integer hard;

    private Integer score;


}
