package com.graduation.education.service.impl;

import com.graduation.education.entity.EduOpenCourse;
import com.graduation.education.dao.EduOpenCourseMapper;
import com.graduation.education.entity.dto.EduOpenCourseDto;
import com.graduation.education.service.EduOpenCourseService;
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
 * @since 2021-03-29
 */
@Service
public class EduOpenCourseServiceImpl extends ServiceImpl<EduOpenCourseMapper, EduOpenCourse> implements EduOpenCourseService {

    @Autowired
    private EduOpenCourseMapper eduOpenCourseMapper;

    @Override
    public int chooseCourse(Integer id) {
        return eduOpenCourseMapper.chooseCourse(id);
    }

    @Override
    public List<EduOpenCourseDto> findStudentCourse(Integer studentId) {
        return eduOpenCourseMapper.findStudentCourse(studentId);
    }
}
