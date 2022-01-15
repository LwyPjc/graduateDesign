package com.graduation.warning.dao;

import com.graduation.warning.entity.OpenCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduation.warning.entity.dto.OpenCourseDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 开课信息 Mapper 接口
 * </p>
 *
 * @author auth
 * @since 2022-02-19
 */
@Mapper
@Repository
public interface OpenCourseMapper extends BaseMapper<OpenCourse> {
    /**
     * 当前选课人数+1
     */
    int chooseCourse(Integer id);

    /**
     * 查找学生所有的课程信息
     */
    List<OpenCourseDto> findStudentCourse(Integer studentId);
}
