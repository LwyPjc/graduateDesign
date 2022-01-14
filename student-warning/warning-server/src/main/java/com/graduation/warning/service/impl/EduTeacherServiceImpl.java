package com.graduation.warning.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.warning.entity.EduTeacher;
import com.graduation.warning.dao.EduTeacherMapper;
import com.graduation.warning.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.warning.util.Constant;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 教师信息 服务实现类
 * </p>
 *
 * @author auth
 * @since 2021-03-29
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public EduTeacher findByCode(String code) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.TEACHER_CODE,code);
        return this.getOne(queryWrapper);
    }
}
