package com.graduation.education.service;

import com.graduation.education.entity.EduClass;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 班级信息 服务类
 * </p>
 *
 * @author auth
 * @since 2021-03-29
 */
public interface EduClassService extends BaseService<EduClass> {

    List<EduClass>  findWithoutTeacherClass();
}
