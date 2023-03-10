package com.tuuli.controller;


import com.tuuli.common.R;
import com.tuuli.dao.CourseDao;
import com.tuuli.domain.Course;
import com.tuuli.domain.Question;
import com.tuuli.dto.CourseManger;
import com.tuuli.dto.QuestionsManger;
import com.tuuli.service.ICourseService;
import com.tuuli.service.IQuestionsService;
import com.tuuli.service.ITeaCourService;
import com.tuuli.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tuuli
 * @since 2023-01-12
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private ICourseService courseService;
    @Autowired
    private ITeaCourService teaCourService;

    @GetMapping("/page")
    public R<CourseManger> getPage(Integer page, Integer pageSize, String name){
        CourseManger page1 = courseService.getPage(page - 1, pageSize, name);
        /*
        查询共同管理该课程的老师
        TODO 该方法效率极低，后续考虑优化或删掉该功能
         */
        for (Course q : page1.getList()) {
            List<String> teaNamesList = teaCourService.getTeaNamesByCourId(q.getId());
            q.setTeaNamesStr(String.join(",",teaNamesList));
        }
        return R.success(page1);
    }

    /**
     * 查询所有的课程id和课程名称，用于新增题目中选择题目所属课程的展示
     * @return
     */
    @GetMapping("/getAll")
    public R<CourseManger> getAll(){
        CourseManger courseManger = courseService.getAll();
        return R.success(courseManger);
    }

}

