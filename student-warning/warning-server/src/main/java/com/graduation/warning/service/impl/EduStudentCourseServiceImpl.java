package com.graduation.warning.service.impl;

import com.google.common.collect.Lists;
import com.graduation.warning.entity.EduOpenCourse;
import com.graduation.warning.entity.EduStudentCourse;
import com.graduation.warning.dao.EduStudentCourseMapper;
import com.graduation.warning.service.EduOpenCourseService;
import com.graduation.warning.service.EduStudentCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 选课信息 服务实现类
 * </p>
 *
 * @author auth
 * @since 2021-03-29
 */
@Service
public class EduStudentCourseServiceImpl extends ServiceImpl<EduStudentCourseMapper, EduStudentCourse> implements EduStudentCourseService {

    @Autowired
    private EduOpenCourseService eduOpenCourseService;
    @Autowired
    private EduStudentCourseMapper eduStudentCourseMapper;

    @Override
    public boolean isCourseConflict(Integer studentId, Integer courseId) {
        //获取开课信息
        EduOpenCourse openCourse = eduOpenCourseService.getById(courseId);
        //取出节次一样的课程信息
        List<EduOpenCourse>  chooseCourse = eduStudentCourseMapper.getCourseByCondition(openCourse.getTerm(),openCourse.getNodeStart(),openCourse.getNodeEnd(),studentId,openCourse.getWeekStart(),openCourse.getWeekEnd());
        //选中的课程周次
        List<String> targetWeekTime = Lists.newArrayList(openCourse.getWeekTime().split(","));
        //判断周次是否一样，一样就返回true
        for(EduOpenCourse course:chooseCourse){
            List<String> weekTimes = Lists.newArrayList(course.getWeekTime().split(","));
            for(String time:targetWeekTime){
                if(weekTimes.contains(time)){
                    return true;
                }
            }
        }
        return false;
    }
}
