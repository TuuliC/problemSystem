package com.tuuli.controller;

import com.tuuli.common.R;
import com.tuuli.domain.Question;
import com.tuuli.dto.QuestionsManger;
import com.tuuli.service.ICourseService;
import com.tuuli.service.IQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
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


    @GetMapping("/page")
    public R<QuestionsManger> getPage(Integer page, Integer pageSize, String name) {
        QuestionsManger page1 = questionsService.getPage(page, pageSize, name);
        //将其中的课程id转为课程名称
        for (Question q : page1.getList()) {
            if (q.getQuesCourId() != null && q.getQuesCourId() != 0) {
                String courseName = courseService.getNameById(q.getQuesCourId());
                q.setQuesCourStr(courseName);
            }
        }

        return R.success(page1);
    }

    @PostMapping("/add")
    public R<String> add(Question question) throws IOException {

        if (question.getFile() != null) {
            String newFileName = savePicture(question.getFile());
            question.setPicture(newFileName);
        }

        questionsService.add(question);
        return R.success("success");
    }

    @DeleteMapping("/delete")
    public R<String> delete(Integer[] ids) {
        /*
        需删除图片文件
        ...
         */
        questionsService.deleteById(ids);
        return R.success("success");
    }

    @GetMapping("/query/{id}")
    public R<Question> query(@PathVariable Integer id) {
        Question question = questionsService.queryById(id);
        return R.success(question);
    }

    @PostMapping("/update")
    public R<String> update(Question question, @RequestParam("isEditFile") String isEditFile) throws IOException {
        if (Objects.equals(isEditFile, "false")) {
            //
        } else if (isEditFile.startsWith("del")) {
            File file = new File("src/main/resources/static/questionsImages/" + isEditFile.substring(3));
            file.delete();
            question.setPicture("");
        } else if (Objects.equals(isEditFile, "add")) {
            String newFileName = savePicture(question.getFile());
            question.setPicture(newFileName);
        } else if (isEditFile.startsWith("edit")){
            File file = new File("src/main/resources/static/questionsImages/" + isEditFile.substring(4));
            file.delete();
            String newFileName = savePicture(question.getFile());
            question.setPicture(newFileName);
        }
        questionsService.update(question);
        return R.success("success");
    }

    private String savePicture(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename(); // 获取文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));// 获取文件的后缀名
        //为防止文件重名被覆盖，为每个文件都生成不同的名字
        UUID uuid = UUID.randomUUID();//生成一个唯一标识符
        String newFileName = uuid.toString().replaceAll("-", "") + suffixName;

        // 文件上传后存储的位置
        File savePos = new File("src/main/resources/static/questionsImages");
        // 获取存放位置的规范路径
        String realPath = savePos.getCanonicalPath();

        File dir = new File(realPath, newFileName);//创建文件流，对文件操作
        File filepath = new File(realPath);
        if (!filepath.exists()) {
            //路径不存在则创建
            filepath.mkdirs();
        }
        file.transferTo(dir);//将文件 传送到前创建的文件流（把图片写进去）

        return newFileName;
    }


}

