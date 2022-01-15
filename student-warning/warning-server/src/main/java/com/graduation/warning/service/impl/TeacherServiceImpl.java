package com.graduation.warning.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.warning.entity.Teacher;
import com.graduation.warning.dao.TeacherMapper;
import com.graduation.warning.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.warning.util.Constant;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 教师信息 服务实现类
 * </p>
 *
 * @author auth
 * @since 2022-02-19
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public Teacher findByCode(String code) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.TEACHER_CODE,code);
        return this.getOne(queryWrapper);
    }
}
