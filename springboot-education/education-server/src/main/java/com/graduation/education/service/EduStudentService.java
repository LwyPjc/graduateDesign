package com.graduation.education.service;

import com.graduation.education.entity.EduStudent;
import com.baomidou.mybatisplus.extension.service.IService;

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
