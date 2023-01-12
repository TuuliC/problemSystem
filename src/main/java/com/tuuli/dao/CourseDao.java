package com.tuuli.dao;

import com.tuuli.domain.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tuuli
 * @since 2023-01-11
 */
@Mapper
public interface CourseDao extends BaseMapper<Course> {

}
