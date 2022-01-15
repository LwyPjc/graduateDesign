package com.graduation.warning.service.impl;

import com.google.common.collect.Lists;
import com.graduation.warning.entity.OpenCourse;
import com.graduation.warning.entity.StudentCourse;
import com.graduation.warning.dao.StudentCourseMapper;
import com.graduation.warning.service.OpenCourseService;
import com.graduation.warning.service.StudentCourseService;
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
 * @since 2022-02-19
 */
@Service
public class StudentCourseServiceImpl extends ServiceImpl<StudentCourseMapper, StudentCourse> implements StudentCourseService {

    @Autowired
    private OpenCourseService openCourseService;
    @Autowired
    private StudentCourseMapper studentCourseMapper;

    @Override
    public boolean isCourseConflict(Integer studentId, Integer courseId) {
        //获取开课信息
        OpenCourse openCourse = openCourseService.getById(courseId);
        //取出节次一样的课程信息
        List<OpenCourse>  chooseCourse = studentCourseMapper.getCourseByCondition(openCourse.getTerm(),openCourse.getNodeStart(),openCourse.getNodeEnd(),studentId,openCourse.getWeekStart(),openCourse.getWeekEnd());
        //选中的课程周次
        List<String> targetWeekTime = Lists.newArrayList(openCourse.getWeekTime().split(","));
        //判断周次是否一样，一样就返回true
        for(OpenCourse course:chooseCourse){
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
