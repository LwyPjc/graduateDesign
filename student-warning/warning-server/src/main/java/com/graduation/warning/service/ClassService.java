package com.graduation.warning.service;

import com.graduation.warning.entity.Class;

import java.util.List;

/**
 * <p>
 * 班级信息 服务类
 * </p>
 *
 * @author auth
 * @since 2022-02-19
 */
public interface ClassService extends BaseService<Class> {

    List<Class>  findWithoutTeacherClass();
}
