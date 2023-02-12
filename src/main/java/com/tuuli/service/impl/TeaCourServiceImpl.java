package com.tuuli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tuuli.dao.TeacherDao;
import com.tuuli.domain.Course;
import com.tuuli.domain.TeaCour;
import com.tuuli.dao.TeaCourDao;
import com.tuuli.domain.Teacher;
import com.tuuli.service.ITeaCourService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class TeaCourServiceImpl extends ServiceImpl<TeaCourDao, TeaCour> implements ITeaCourService {

    @Autowired
    private TeaCourDao teaCourDao;
    @Autowired
    private TeacherDao teacherDao;

    /**
     * 通过课程id查询出管理该课程的老师名字，效率极低，需优化
     * @param courseId 课程id
     * @return 管理该课程的老师名字的集合
     */
    @Override
    public List<String> getTeaNamesByCourId(Integer courseId) {
        //查询管理该课程的所有老师的id
        LambdaQueryWrapper<TeaCour> teaCourLambdaQueryWrapper = new LambdaQueryWrapper<>();//查询条件对象
        teaCourLambdaQueryWrapper.select(TeaCour::getTeaId).eq(TeaCour::getCourId, courseId);//设置查询条件
        List<TeaCour> list = teaCourDao.selectList(teaCourLambdaQueryWrapper);
        //将查询出来的所有老师的id转为name
        List<String> teaNamesList = new ArrayList<>();
        for (TeaCour t:list) {
            LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();//查询条件对象
            teacherLambdaQueryWrapper.select(Teacher::getName).eq(Teacher::getId, t.getCourId());//设置查询条件
            Teacher teacher = teacherDao.selectById(t.getTeaId());
            teaNamesList.add(teacher.getName());
        }
        return teaNamesList;
    }
}
