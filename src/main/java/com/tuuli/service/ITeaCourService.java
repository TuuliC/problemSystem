package com.tuuli.service;

import com.tuuli.domain.TeaCour;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tuuli
 * @since 2023-01-12
 */
public interface ITeaCourService extends IService<TeaCour> {
    List<String> getTeaNamesByCourId(Integer courseId);
}
