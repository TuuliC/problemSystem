package com.tuuli.controller;

import com.tuuli.common.R;
import com.tuuli.domain.Question;
import com.tuuli.dto.QuestionsManger;
import com.tuuli.service.ICourseService;
import com.tuuli.service.IQuestionsService;
import com.tuuli.util.PictureHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

    /**
     * 主查询函数，包括模糊查询和分页查询
     *
     * @param page
     * @param pageSize
     * @param name     模糊查询“题目描述”
     * @return
     */
    @GetMapping("/page")
    public R<QuestionsManger> getPage(Integer page, Integer pageSize, String name) {
        QuestionsManger page1 = questionsService.getPage(page, pageSize, name);
        //以下为将其中的课程id转为课程名称
        List<Integer> ids = new ArrayList<>();
        for (Question    q : page1.getList()) {//获取所有的课程id
            if (q.getQuesCourId() != null && q.getQuesCourId() != 0 && !ids.contains(q.getQuesCourId())) {
                ids.add(q.getQuesCourId());
            }
        }

        /*
        事实上 前端已作规定，ids集合永远不会为空。但如果不作判断，在分页查询时，页数选择第一页以后，同时每页展示条数的选择大于数据总条数时，会出现未知bug
        即 page > 1 && pageSize > 数据总条数 时出现未知bug
        例：总共有15条数据，展示时默认每页展示10条数据，若用户在查看第2页时，切换为每页查看20条数据，则出现未知bug
         */
        if (!ids.isEmpty()) {
            //通过课程id查询对应的课程名称，以key-value形式保存到Map中
            Map<Integer, String> names = courseService.getNamesByIds(ids);
            //将课程名称写入到数据集中
            for (Question q : page1.getList()) {
                q.setQuesCourStr(names.get(q.getQuesCourId()));
            }
        }

        return R.success(page1);
    }

    /**
     * 新增数据
     *
     * @param question 题目对象
     * @return
     */
    @PostMapping("/add")
    public R<String> add(Question question) {

        //题目图片不为空时保存题目图片
        if (question.getFile() != null) {
            String newFileName = null;
            try {
                newFileName = PictureHandle.savePicture(question.getFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
            question.setPicture(newFileName);
        }

        questionsService.add(question);
        return R.success("success");
    }

    /**
     * 删除数据，包括删除单个和批量删除
     *
     * @param ids 需删除的题目id的集合
     * @return
     */
    @DeleteMapping("/delete")
    public R<String> delete(Integer[] ids) {
        /*
        TODO 需删除图片文件，尚未完成
        ...
         */
        questionsService.deleteByIds(ids);
        return R.success("success");
    }

    /**
     * 通过题目id查询单个数据
     *
     * @param id 题目id
     * @return
     */
    @GetMapping("/query/{id}")
    public R<Question> query(@PathVariable Integer id) {
        Question question = questionsService.queryById(id);
        return R.success(question);
    }

    /**
     * 修改题目数据
     * isEditFile参数错误时报异常
     *
     * @param question   修改的题目对象
     * @param isEditFile 图片的修改状态标识，false-未修改图片，del-删除图片，add新增图片，edit-更换图片，
     *                   除false和add状态外，标识后面还带有原图片的名称，例：isEditFile = edit5dj3p5.png
     * @return
     */
    @PostMapping("/update")
    public R<String> update(Question question, @RequestParam("isEditFile") String isEditFile) {
        //判断图片的修改状态
        if (Objects.equals(isEditFile, "false")) {//不作修改
            //
        } else if (isEditFile.startsWith("del")) {//删除
            File file = new File("src/main/resources/static/questionsImages/" + isEditFile.substring(3));
            file.delete();
            question.setPicture("");
        } else if (Objects.equals(isEditFile, "add")) {//新增
            String newFileName = null;
            try {
                newFileName = PictureHandle.savePicture(question.getFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
            question.setPicture(newFileName);
        } else if (isEditFile.startsWith("edit")) {//更换 == 删除 + 新增
            File file = new File("src/main/resources/static/questionsImages/" + isEditFile.substring(4));
            file.delete();
            String newFileName = null;
            try {
                newFileName = PictureHandle.savePicture(question.getFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
            question.setPicture(newFileName);
        } else throw new IllegalArgumentException("修改题目数据时前端传参错误！");

        questionsService.update(question);
        return R.success("success");
    }

    /**
     * 组建试卷
     *
     * @param ids 题目id集合
     * @return
     */
    @GetMapping("/buildTest")
    private R<String> buildTest(Integer[] ids) {
        System.out.println("ids = " + Arrays.toString(ids));
        //调用python脚本
        questionsService.buildTestCallPython(ids);
        return R.success("success");
    }
}

