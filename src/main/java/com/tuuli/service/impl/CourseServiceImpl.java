package com.tuuli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuuli.dao.CourseDao;
import com.tuuli.domain.Course;
import com.tuuli.dto.CourseManger;
import com.tuuli.service.ICourseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tuuli
 * @since 2023-01-12
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseDao, Course> implements ICourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public CourseManger getPage(Integer page, Integer pageSize, String name) {
        IPage<Course> page1 = new Page<>(page,pageSize);//设置分页
        LambdaQueryWrapper<Course> courseLambdaQueryWrapper = new LambdaQueryWrapper<>();//查询条件对象
        courseLambdaQueryWrapper.like(!StringUtils.isBlank(name),Course::getName,name);//设置查询条件
        courseDao.selectPage(page1, courseLambdaQueryWrapper);
        Integer total = Math.toIntExact(page1.getTotal());

        CourseManger t = new CourseManger();//封装查询数据对象
        t.setTotal(total);
        t.setList(page1.getRecords());

        return t;
    }

    @Override
    public String getNameById(Integer courseId) {
        LambdaQueryWrapper<Course> courseLambdaQueryWrapper = new LambdaQueryWrapper<>();//查询条件对象
        courseLambdaQueryWrapper.select(Course::getName).eq(Course::getId, courseId);//设置查询的字段
        Course course = courseDao.selectOne(courseLambdaQueryWrapper);
        return course.getName();
    }

    @Override
    public CourseManger getAll() {
        LambdaQueryWrapper<Course> courseLambdaQueryWrapper = new LambdaQueryWrapper<>();//查询条件对象
        courseLambdaQueryWrapper.select(Course::getId,Course::getName);//设置查询条件
        List<Course> courseList = courseDao.selectList(courseLambdaQueryWrapper);

        CourseManger t = new CourseManger();
        t.setTotal(courseList.size());
        t.setList(courseList);
        return t;
    }
}
