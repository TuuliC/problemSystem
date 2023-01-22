package com.tuuli.service;

import com.tuuli.domain.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuuli.dto.CourseManger;
import com.tuuli.dto.QuestionsManger;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tuuli
 * @since 2023-01-12
 */
public interface ICourseService extends IService<Course> {
    CourseManger getPage(Integer page, Integer pageSize, String name);
    String getNameById(Integer courseId);
    CourseManger getAll();
}
