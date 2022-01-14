package com.graduation.warning.service;

import com.graduation.warning.entity.EduStudent;

/**
 * <p>
 * 学生信息 服务类
 * </p>
 *
 * @author auth
 * @since 2021-03-29
 */
public interface EduStudentService extends BaseService<EduStudent> {
    /**
     * 根据学号查找学生信息
     */
    EduStudent findByStuCode(String code);
}
