package com.tuuli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuuli.domain.Question;
import com.tuuli.dto.QuestionsManger;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tuuli
 * @since 2023-01-12
 */
public interface IQuestionsService extends IService<Question> {
    QuestionsManger getPage(Integer page, Integer pageSize, String name);
    void add(Question question);
    void deleteByIds(Integer[] ids);
    Question queryById(Integer id);
    void update(Question question);
    String getPictureById(Integer id);

}
