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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 主查询方法，包括模糊查询、分页查询
     * @param page
     * @param pageSize
     * @param name 模糊查询“题目描述”
     * @return Manger数据集，包括数据集合，数据条数
     */
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

    /**
     * 将课程id查询到课程名称
     * @param courseIds 需要查询的课程id
     * @return 以Map<Integer, String>类型返回，key为课程id，value为对应的课程名称
     */
    @Override
    public Map<Integer, String> getNamesByIds(List<Integer> courseIds) {
        LambdaQueryWrapper<Course> courseLambdaQueryWrapper = new LambdaQueryWrapper<>();//查询条件对象
        courseLambdaQueryWrapper.in(Course::getId, courseIds);//设置查询的字段
        List<Course> course = courseDao.selectList(courseLambdaQueryWrapper);

        Map<Integer,String> map = new HashMap<>();
        for (Course c: course) {
            map.put(c.getId(),c.getName());
        }

        return map;
    }

    /**
     * 查询所有的课程id和课程名称
     * @return
     */
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
