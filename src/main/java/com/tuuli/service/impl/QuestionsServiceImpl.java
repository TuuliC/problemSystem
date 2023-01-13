package com.tuuli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuuli.domain.Question;
import com.tuuli.dao.QuestionsDao;
import com.tuuli.dto.QuestionsManger;
import com.tuuli.service.IQuestionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tuuli
 * @since 2023-01-12
 */
@Service
public class QuestionsServiceImpl extends ServiceImpl<QuestionsDao, Question> implements IQuestionsService {
    @Autowired
    private QuestionsDao questionsDao;

    @Override
    public QuestionsManger getPage(Integer page, Integer pageSize, String name){
        IPage<Question> page1 = new Page<>(page,pageSize);
        LambdaQueryWrapper<Question> questionsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        questionsLambdaQueryWrapper.like(name!=null,Question::getDescribe,name);
        questionsDao.selectPage(page1, questionsLambdaQueryWrapper);
        Integer total = Math.toIntExact(page1.getTotal());
        QuestionsManger t = new QuestionsManger();
        t.setTotal(total);
        t.setList(page1.getRecords());

        return t;
    }
}
