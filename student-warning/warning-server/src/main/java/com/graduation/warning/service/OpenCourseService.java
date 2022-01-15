package com.graduation.warning.service;

import com.graduation.warning.entity.OpenCourse;
import com.graduation.warning.entity.dto.OpenCourseDto;

import java.util.List;

/**
 * <p>
 * 开课信息 服务类
 * </p>
 *
 * @author auth
 * @since 2022-02-19
 */
public interface OpenCourseService extends BaseService<OpenCourse> {

    /**
     * 当前选课人数+1
     */
    int chooseCourse(Integer id);
    /**
     * 查找学生所有的课程信息
     */
    List<OpenCourseDto> findStudentCourse(Integer studentId);
}
