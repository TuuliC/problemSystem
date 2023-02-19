package com.tuuli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuuli.dao.QuestionsDao;
import com.tuuli.domain.Question;
import com.tuuli.dto.QuestionsManger;
import com.tuuli.service.IQuestionsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tuuli
 * @since 2023-01-12
 */
@Service
public class QuestionsServiceImpl extends ServiceImpl<QuestionsDao, Question> implements IQuestionsService {
    @Autowired
    private QuestionsDao questionsDao;

    /**
     * 主查询方法，包括模糊查询、分页查询
     *
     * @param page
     * @param pageSize
     * @param name     模糊查询“题目描述”
     * @return Manger数据集，包括数据集合，数据条数
     */
    @Override
    public QuestionsManger getPage(Integer page, Integer pageSize, String name) {
        IPage<Question> page1 = new Page<>(page, pageSize);//设置分页
        LambdaQueryWrapper<Question> questionsLambdaQueryWrapper = new LambdaQueryWrapper<>();//查询条件对象
        questionsLambdaQueryWrapper.like(!StringUtils.isBlank(name), Question::getDescription, name);//设置查询条件（模糊查询）
        questionsDao.selectPage(page1, questionsLambdaQueryWrapper);//查询
        Integer total = Math.toIntExact(page1.getTotal());

        QuestionsManger t = new QuestionsManger();//封装查询数据对象
        t.setTotal(total);
        t.setList(page1.getRecords());

        return t;
    }

    /**
     * 新增数据
     *
     * @param question 题目对象
     */
    @Override
    public void add(Question question) {
        questionsDao.insert(question);
    }

    /**
     * 通过题目id删除数据，包括批量删除
     *
     * @param ids 需删除的题目id的集合
     */
    @Override
    public void deleteByIds(Integer[] ids) {
        questionsDao.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 通过题目id查询该题目的信息
     *
     * @param id 题目id
     * @return Question对象
     */
    @Override
    public Question queryById(Integer id) {
        LambdaQueryWrapper<Question> questionsLambdaQueryWrapper = new LambdaQueryWrapper<>();//查询条件对象
        questionsLambdaQueryWrapper.eq(Question::getId, id);//设置查询条件
        Question question = questionsDao.selectOne(questionsLambdaQueryWrapper);
        return question;
    }

    @Override
    public void update(Question question) {
        questionsDao.updateById(question);
    }

    /**
     * TODO 该方法暂未使用
     * 通过题目id查询改题目的图片名称
     *
     * @param id 题目id
     * @return 该题目有图片时返回图片名字，否则返回空字符串
     */
    @Override
    public String getPictureById(Integer id) {
        LambdaQueryWrapper<Question> questionsLambdaQueryWrapper = new LambdaQueryWrapper<>();//查询条件对象
        questionsLambdaQueryWrapper.select(Question::getPicture).eq(Question::getId, id);//设置查询条件
        Question question = questionsDao.selectOne(questionsLambdaQueryWrapper);
        return question.getPicture() == null ? "" : question.getPicture();
    }

    /**
     * 调用python脚本进行组建试卷
     *
     * @param ids 题目id集合
     */
    @Override
    public void buildTestCallPython(Integer[] ids) {
        /*
        调用python
         */
    }
}
