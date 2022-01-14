package com.graduation.warning.service;

import com.graduation.warning.entity.EduOpenCourse;
import com.graduation.warning.entity.dto.EduOpenCourseDto;

import java.util.List;

/**
 * <p>
 * 开课信息 服务类
 * </p>
 *
 * @author auth
 * @since 2021-03-29
 */
public interface EduOpenCourseService extends BaseService<EduOpenCourse> {

    /**
     * 当前选课人数+1
     */
    int chooseCourse(Integer id);
    /**
     * 查找学生所有的课程信息
     */
    List<EduOpenCourseDto> findStudentCourse(Integer studentId);
}
