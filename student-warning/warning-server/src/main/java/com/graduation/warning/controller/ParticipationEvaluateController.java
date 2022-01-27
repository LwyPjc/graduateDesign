package com.graduation.warning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.warning.entity.Course;
import com.graduation.warning.entity.OpenCourse;
import com.graduation.warning.entity.Student;
import com.graduation.warning.service.*;
import com.graduation.warning.util.Constant;
import com.graduation.warning.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.graduation.warning.entity.ParticipationEvaluate;

import java.beans.PropertyEditorSupport;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>标题: 服务 - 请求控制层</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: mic
 * @date 2022-1-17
 */
@RestController
@RequestMapping("/participationEvaluate")
@CrossOrigin
public class ParticipationEvaluateController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private OpenCourseService openCourseService;

    @Autowired
    private ParticipationEvaluateService participationEvaluateService;

    @Autowired
    private StudentStatisticsService studentStatisticsService;

    @GetMapping("/findList")
    public List<ParticipationEvaluate> findList(ParticipationEvaluate participationEvaluate) {
        return participationEvaluateService.findList(participationEvaluate);
    }

    @GetMapping("/findListByPage")
    public Page<ParticipationEvaluate> findListByPage(ParticipationEvaluate participationEvaluate, Page page) {
        QueryWrapper<ParticipationEvaluate> queryWrapper = new QueryWrapper<>(participationEvaluate);
        queryWrapper.eq(Constant.TEACHER_NAME, participationEvaluate.getTeacherName());
        return participationEvaluateService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public ParticipationEvaluate getById(@PathVariable String id) {
        return participationEvaluateService.getById(id);
    }

    @PostMapping("/save")
    public ResultMap save(ParticipationEvaluate participationEvaluate) {
        // 校验此学生缺勤记录是否已经纯在
        ResultMap resultMap = new ResultMap();
        Integer openCourseId = participationEvaluate.getOpenCourseId();
        Integer studentId = participationEvaluate.getStudentId();
        OpenCourse byId = openCourseService.getById(openCourseId);
        Integer classId = byId.getCourseId();
        Course course = courseService.getById(classId);
        Student student = studentService.getById(studentId);
        participationEvaluate.setStudentName(student.getStuName());
        participationEvaluate.setCourseName(course.getName());
        ParticipationEvaluate participationEvaluateCondition = new ParticipationEvaluate();
        QueryWrapper<ParticipationEvaluate> queryWrapper = new QueryWrapper<>(participationEvaluateCondition);
        // 开课id 教师 学生id校验唯一
        queryWrapper.eq(Constant.OPEN_COURSE_ID, participationEvaluate.getOpenCourseId());
        queryWrapper.eq(Constant.TEACHER_NAME, participationEvaluate.getTeacherName());
        queryWrapper.eq(Constant.STU_ID, participationEvaluate.getStudentId());
        int count = participationEvaluateService.count(queryWrapper);
        if (count > 0) {
            return resultMap.setError("此纪录已存在，请使用编辑操作!");
        }
        participationEvaluateService.save(participationEvaluate);
        // 发送统计值
        studentStatisticsService.handleParticipation(participationEvaluate);
        return resultMap.setSuccss("保存成功");
    }

    @PostMapping("/edit")
    public boolean edit(ParticipationEvaluate participationEvaluate) {
        studentStatisticsService.handleParticipation(participationEvaluate);
        return participationEvaluateService.updateById(participationEvaluate);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return participationEvaluateService.removeById(id);
    }

    @InitBinder
    public void initBinder(final WebDataBinder webdataBinder) {
        webdataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(new Date(Long.valueOf(text)));
            }
        });
    }
}
