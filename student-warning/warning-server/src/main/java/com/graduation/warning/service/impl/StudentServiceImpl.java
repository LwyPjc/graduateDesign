package com.graduation.warning.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.warning.entity.Student;
import com.graduation.warning.dao.StudentMapper;
import com.graduation.warning.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.warning.util.Constant;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生信息 服务实现类
 * </p>
 *
 * @author auth
 * @since 2022-02-19
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Override
    public Student findByStuCode(String code) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.STU_CODE,code);
        return this.getOne(queryWrapper);
    }
}
