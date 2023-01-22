package com.tuuli.controller;

import com.alibaba.fastjson.JSON;
import com.tuuli.common.R;
import com.tuuli.domain.Question;
import com.tuuli.dto.Options;
import com.tuuli.dto.QuestionDto;
import com.tuuli.dto.QuestionsManger;
import com.tuuli.service.ICourseService;
import com.tuuli.service.IQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/questions")
public class QuestionsController {
    @Autowired
    private IQuestionsService questionsService;
    @Autowired
    private ICourseService courseService;

    @GetMapping ("/page")
    public R<QuestionsManger> getPage(Integer page, Integer pageSize, String name){
        QuestionsManger page1 = questionsService.getPage(page - 1, pageSize, name);
        //将其中的课程id转为课程名称
        for (Question q : page1.getList()) {
            String courseName = courseService.getNameById(q.getQuesCourId());
            q.setQuesCourStr(courseName);
        }
        return R.success(page1);
    }

    @PostMapping("/add")
    public R<QuestionsManger> add( QuestionDto questionDto){
        List<Options> options = JSON.parseArray(questionDto.getOptions(), Options.class);
        System.out.println(options);
        System.out.println("---");
        System.out.println(questionDto);
        return null;
    }

}

