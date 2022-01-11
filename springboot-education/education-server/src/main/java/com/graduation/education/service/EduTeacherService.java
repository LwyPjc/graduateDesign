package com.graduation.education.service;

import com.graduation.education.entity.EduStudent;
import com.graduation.education.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 教师信息 服务类
 * </p>
 *
 * @author auth
 * @since 2021-03-29
 */
public interface EduTeacherService extends BaseService<EduTeacher> {
    /**
     * 根据工号查找教师信息
     */
    EduTeacher findByCode(String code);
}
