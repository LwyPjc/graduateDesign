package com.graduation.education.service;

import com.graduation.education.entity.EduStudentCourse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 选课信息 服务类
 * </p>
 *
 * @author auth
 * @since 2021-03-29
 */
public interface EduStudentCourseService extends BaseService<EduStudentCourse> {

    /**
     * 判断某个学生选的课是否与已选的课冲突（周 节 一样的）
     */
    boolean isCourseConflict(Integer studentId,Integer courseId);
}
