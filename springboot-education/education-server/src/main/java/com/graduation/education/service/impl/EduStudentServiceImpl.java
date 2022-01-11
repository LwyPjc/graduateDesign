package com.graduation.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.education.entity.EduStudent;
import com.graduation.education.dao.EduStudentMapper;
import com.graduation.education.service.EduStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.education.util.Constant;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生信息 服务实现类
 * </p>
 *
 * @author auth
 * @since 2021-03-29
 */
@Service
public class EduStudentServiceImpl extends ServiceImpl<EduStudentMapper, EduStudent> implements EduStudentService {

    @Override
    public EduStudent findByStuCode(String code) {
        QueryWrapper<EduStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.STU_CODE,code);
        return this.getOne(queryWrapper);
    }
}
