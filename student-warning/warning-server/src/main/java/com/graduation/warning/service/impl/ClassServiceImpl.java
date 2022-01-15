package com.graduation.warning.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.warning.dao.ClassMapper;
import com.graduation.warning.entity.Class;
import com.graduation.warning.service.ClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.warning.util.Constant;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 班级信息 服务实现类
 * </p>
 *
 * @author auth
 * @since 2022-02-19
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {

    @Override
    public List<Class> findWithoutTeacherClass() {
        QueryWrapper<Class> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull(Constant.CLASS_COUNSELOR);
        return this.list(queryWrapper);
    }
}
