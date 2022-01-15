package com.graduation.warning.service.impl;

import com.graduation.warning.entity.OpenCourse;
import com.graduation.warning.dao.OpenCourseMapper;
import com.graduation.warning.entity.dto.OpenCourseDto;
import com.graduation.warning.service.OpenCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 开课信息 服务实现类
 * </p>
 *
 * @author auth
 * @since 2022-02-19
 */
@Service
public class OpenCourseServiceImpl extends ServiceImpl<OpenCourseMapper, OpenCourse> implements OpenCourseService {

    @Autowired
    private OpenCourseMapper openCourseMapper;

    @Override
    public int chooseCourse(Integer id) {
        return openCourseMapper.chooseCourse(id);
    }

    @Override
    public List<OpenCourseDto> findStudentCourse(Integer studentId) {
        return openCourseMapper.findStudentCourse(studentId);
    }
}
