package com.graduation.warning.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.warning.entity.EduClass;
import com.graduation.warning.dao.EduClassMapper;
import com.graduation.warning.service.EduClassService;
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
 * @since 2021-03-29
 */
@Service
public class EduClassServiceImpl extends ServiceImpl<EduClassMapper, EduClass> implements EduClassService {

    @Override
    public List<EduClass> findWithoutTeacherClass() {
        QueryWrapper<EduClass> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull(Constant.CLASS_COUNSELOR);
        return this.list(queryWrapper);
    }
}
