package com.graduation.education.dao;

import com.graduation.education.entity.EduOpenCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduation.education.entity.dto.EduOpenCourseDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 开课信息 Mapper 接口
 * </p>
 *
 * @author auth
 * @since 2021-03-29
 */
@Mapper
@Repository
public interface EduOpenCourseMapper extends BaseMapper<EduOpenCourse> {
    /**
     * 当前选课人数+1
     */
    int chooseCourse(Integer id);

    /**
     * 查找学生所有的课程信息
     */
    List<EduOpenCourseDto> findStudentCourse(Integer studentId);
}
