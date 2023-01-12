package com.tuuli.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author tuuli
 * @since 2023-01-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_questions")
public class Questions implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String describe;

    private String picture;

    private String option;

    private String answer;

    private Integer ques_cour;

    private String chapter;

    @TableLogic
    private Integer delete;

    private Integer hard;


}
