package com.graduation.warning.dao;

import com.graduation.warning.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课表信息 Mapper 接口
 * </p>
 *
 * @author auth
 * @since 2022-02-19
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

}
