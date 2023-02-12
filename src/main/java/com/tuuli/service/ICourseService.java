package com.tuuli.service;

import com.tuuli.domain.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuuli.dto.CourseManger;

import java.util.List;
import java.util.Map;

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
    Map<Integer, String> getNamesByIds(List<Integer> courseId);
    CourseManger getAll();
}
