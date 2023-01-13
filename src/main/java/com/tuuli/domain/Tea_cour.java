package com.tuuli.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("tb_tea_cour")
public class Tea_cour implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "tea_id")
    private Integer teaId;
    @TableField(value = "cour_id")
    private Integer courId;


}
