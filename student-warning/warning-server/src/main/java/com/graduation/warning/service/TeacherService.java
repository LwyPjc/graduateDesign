package com.graduation.warning.service;

import com.graduation.warning.entity.Teacher;

/**
 * <p>
 * 教师信息 服务类
 * </p>
 *
 * @author auth
 * @since 2022-02-19
 */
public interface TeacherService extends BaseService<Teacher> {
    /**
     * 根据工号查找教师信息
     */
    Teacher findByCode(String code);
}
