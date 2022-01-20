package com.graduation.warning.controller;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.google.common.collect.Lists;
import com.graduation.warning.entity.Student;
import com.graduation.warning.entity.StudentCourse;
import com.graduation.warning.entity.dto.OpenCourseDto;
import com.graduation.warning.service.OpenCourseService;
import com.graduation.warning.service.StudentCourseService;
import com.graduation.warning.service.StudentService;
import com.graduation.warning.service.StudentStatisticsService;
import com.graduation.warning.util.Constant;
import com.graduation.warning.util.ResultMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 选课信息 前端控制器
 * </p>
 *
 * @author auth
 * @since 2022-02-19
 */
@Controller
@ResponseBody
@RequestMapping("/studentCourse")
@CrossOrigin
public class StudentCourseController {
    @Autowired
    private StudentCourseService studentCourseService;
    @Autowired
    private OpenCourseService openCourseService;
    @Autowired
    private StudentService studentService;


    @GetMapping("/findList")
    public List<StudentCourse> findList(StudentCourse studentCourse) {
        return studentCourseService.findList(studentCourse);
    }

    @GetMapping("/findListByPage")
    public Page<StudentCourse> findListByPage(StudentCourse studentCourse, Page page) {
        QueryWrapper<StudentCourse> queryWrapper = new QueryWrapper<>(studentCourse);
        return studentCourseService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public StudentCourse getById(@PathVariable String id) {
        return studentCourseService.getById(id);
    }

    @PostMapping("/save")
    @Transactional
    public ResultMap save(StudentCourse studentCourse, String userName) {
        ResultMap resultMap = new ResultMap();
        if(StringUtils.isNotEmpty(userName)){
            Student student = studentService.findByStuCode(userName);
            studentCourse.setStudentId(student.getId());
            //如果课程冲突，则直接返回错误信息
            if(studentCourseService.isCourseConflict(student.getId(), studentCourse.getOpenCourseId())){
                resultMap.setError("所选课程的上课时间与已有课程冲突，请重新选择");
                return resultMap;
            }
            //将开课信息表中的选课人数+1
            openCourseService.chooseCourse(studentCourse.getOpenCourseId());
        }
        studentCourseService.save(studentCourse);
        resultMap.setSuccss("选课成功");
        return resultMap;
    }

    @PostMapping("/edit")
    public boolean edit(StudentCourse studentCourse) {
        return studentCourseService.updateById(studentCourse);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return studentCourseService.removeById(id);
    }

    /**
     * 查询当前登录用户的课程表
     */
    @GetMapping("/timetable")
    public String timetable(String userName){
        //根据学号查出学生用户id
        Student student = studentService.findByStuCode(userName);
        //查询出当前学生所有的课程
        List<OpenCourseDto> courseDtos = openCourseService.findStudentCourse(student.getId());
        //一周五天 一天十节课，构建一个二维数组
        String[][] timetable = new String[5][10];
        //初始化数组
        for(int i = 0;i<5;i++){
            for(int j = 0;j<10;j++){
                timetable[i][j] = "";
            }
        }
        for(OpenCourseDto courseDto:courseDtos){
            String courseInfo = courseDto.getWeekStart() + "-" + courseDto.getWeekEnd() + "\r\n" + courseDto.getCourseName() + "\r\n"
                    +courseDto.getRoomName() + "\r\n" +  courseDto.getTeacherName();
            //对每个节次都设置一条课程信息
            for(int i = courseDto.getNodeStart();i<= courseDto.getNodeEnd(); i++){
                //对每个周次都设置一条课程信息
                List<String> weekTime = Lists.newArrayList(courseDto.getWeekTime().split(Constants.COMMA));
                for(String time:weekTime){
                    timetable[Integer.valueOf(time)-1][i-1] = courseInfo;
                }
            }
        }
        return JSONUtil.toJsonStr(timetable);
    }

}

