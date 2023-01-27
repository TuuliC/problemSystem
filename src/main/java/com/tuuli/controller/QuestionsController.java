package com.tuuli.controller;

import com.tuuli.common.R;
import com.tuuli.domain.Question;
import com.tuuli.dto.QuestionDto;
import com.tuuli.dto.QuestionsManger;
import com.tuuli.service.ICourseService;
import com.tuuli.service.IQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
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

    String delimiter = "#,;%*@";//题目选项由数组类型转为String的分隔符，前端也使用该分隔符

    @GetMapping("/page")
    public R<QuestionsManger> getPage(Integer page, Integer pageSize, String name) {
        QuestionsManger page1 = questionsService.getPage(page - 1, pageSize, name);
        //将其中的课程id转为课程名称
        for (Question q : page1.getList()) {
            String courseName = courseService.getNameById(q.getQuesCourId());
            q.setQuesCourStr(courseName);
        }

        return R.success(page1);
    }

    @PostMapping("/add")
    public R<String> add(QuestionDto questionDto) throws IOException {

        Question question = new Question();

        if (questionDto.getFile()!=null){
            String fileName = questionDto.getFile()[0].getOriginalFilename(); // 获取文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));// 获取文件的后缀名
            //为防止文件重名被覆盖，为每个文件都生成不同的名字
            UUID uuid = UUID.randomUUID();//生成一个唯一标识符
            String newFileName = uuid.toString().replaceAll("-", "") + suffixName;
            //System.out.println("新文件名newFileName：" + newFileName);

            // 文件上传后存储的位置
            File savePos = new File("src/main/resources/static/questionsImages");
            // 获取存放位置的规范路径
            String realPath = savePos.getCanonicalPath();

            File dir = new File(realPath, newFileName);//创建文件流，对文件操作
            File filepath = new File(realPath);
            if (!filepath.exists()) {
                filepath.mkdirs();
            }
            questionDto.getFile()[0].transferTo(dir);//将文件 传送到前创建的文件流（把图片写进去）

            question.setPicture(newFileName);
        }

        question.setDescription(questionDto.getDescription());
        question.setOptions(String.join(delimiter, questionDto.getOptions()));
        question.setAnswer(questionDto.getAnswer());
        question.setQuesCourId(questionDto.getQuesCourId());
        question.setChapter(questionDto.getChapter());
        question.setHard(questionDto.getHard());
        question.setScore(questionDto.getScore());

        questionsService.add(question);
        return R.success("success");
    }

}

