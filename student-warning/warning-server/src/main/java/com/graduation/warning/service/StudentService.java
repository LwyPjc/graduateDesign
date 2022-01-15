package com.graduation.warning.service;

import com.graduation.warning.entity.Student;

/**
 * <p>
 * 学生信息 服务类
 * </p>
 *
 * @author auth
 * @since 2022-02-19
 */
public interface StudentService extends BaseService<Student> {
    /**
     * 根据学号查找学生信息
     */
    Student findByStuCode(String code);
}
