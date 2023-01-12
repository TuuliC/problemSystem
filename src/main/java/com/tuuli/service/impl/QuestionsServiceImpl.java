package com.tuuli.service.impl;

import com.tuuli.domain.Questions;
import com.tuuli.dao.QuestionsDao;
import com.tuuli.service.IQuestionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tuuli
 * @since 2023-01-11
 */
@Service
public class QuestionsServiceImpl extends ServiceImpl<QuestionsDao, Questions> implements IQuestionsService {

}
