package com.tuuli.dto;

import com.tuuli.domain.Course;
import com.tuuli.domain.Question;
import lombok.Data;

import java.util.List;

/**
 * @author tuuli
 * @time Created in 2023/1/13 22:25
 * @description
 */
@Data
public class CourseManger {

    private List<Course> list;

    private Integer total;
}
