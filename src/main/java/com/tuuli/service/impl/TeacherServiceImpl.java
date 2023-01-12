package com.tuuli.service.impl;

import com.tuuli.domain.Teacher;
import com.tuuli.dao.TeacherDao;
import com.tuuli.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class TeacherServiceImpl extends ServiceImpl<TeacherDao, Teacher> implements ITeacherService {

}
