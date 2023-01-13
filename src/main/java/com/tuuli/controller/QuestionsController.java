package com.tuuli.controller;


import com.tuuli.common.R;
import com.tuuli.dto.QuestionsManger;
import com.tuuli.service.IQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tuuli
 * @since 2023-01-12
 */
@Controller
@RequestMapping("/questions")
public class QuestionsController {
    @Autowired
    private IQuestionsService questionsService;

    @GetMapping ("/page")
    public R<QuestionsManger> getPage(Integer page, Integer pageSize, String name){
        QuestionsManger page1 = questionsService.getPage(page - 1, pageSize, name);
        return R.success(page1);
    }


}

