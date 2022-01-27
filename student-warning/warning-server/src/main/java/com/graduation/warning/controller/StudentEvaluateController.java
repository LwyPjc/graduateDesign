package com.graduation.warning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.warning.entity.*;
import com.graduation.warning.service.*;
import com.graduation.warning.util.Constant;
import com.graduation.warning.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
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
@RequestMapping("/studentEvaluate")
@CrossOrigin
public class StudentEvaluateController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private OpenCourseService openCourseService;

    @Autowired
    private StudentEvaluateService studentEvaluateService;

    @Autowired
    private StudentStatisticsService studentStatisticsService;

    @GetMapping("/findList")
    public List<StudentEvaluate> findList(StudentEvaluate studentEvaluate) {
        return studentEvaluateService.findList(studentEvaluate);
    }

    @GetMapping("/findListByPage")
    public Page<StudentEvaluate> findListByPage(StudentEvaluate studentEvaluate, Page page) {
        QueryWrapper<StudentEvaluate> queryWrapper = new QueryWrapper<>(studentEvaluate);
        queryWrapper.eq(Constant.TEACHER_NAME, studentEvaluate.getTeacherName());
        return studentEvaluateService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public StudentEvaluate getById(@PathVariable String id) {
        return studentEvaluateService.getById(id);
    }

    @PostMapping("/save")
    public ResultMap save(StudentEvaluate studentEvaluate) {
        // 校验学生评价记录是否已存在
        ResultMap resultMap = new ResultMap();
        Integer openCourseId = studentEvaluate.getOpenCourseId();
        Integer studentId = studentEvaluate.getStudentId();
        OpenCourse byId = openCourseService.getById(openCourseId);
        Integer classId = byId.getCourseId();
        Course course = courseService.getById(classId);
        Student student = studentService.getById(studentId);
        studentEvaluate.setStudentName(student.getStuName());
        studentEvaluate.setCourseName(course.getName());
        QueryWrapper<StudentEvaluate> queryWrapper = new QueryWrapper<>(studentEvaluate);
        // 开课id 教师 学生校验
        queryWrapper.eq(Constant.OPEN_COURSE_ID, studentEvaluate.getOpenCourseId());
        queryWrapper.eq(Constant.TEACHER_NAME, studentEvaluate.getTeacherName());
        queryWrapper.eq(Constant.STU_ID, studentEvaluate.getStudentId());
        int count = studentEvaluateService.count(queryWrapper);
        if (count > 0) {
            return resultMap.setError("此纪录已存在，请使用编辑操作!");
        }
        // 保存记录
        studentEvaluateService.save(studentEvaluate);
        return resultMap;
    }

    @PostMapping("/edit")
    public boolean edit(StudentEvaluate studentEvaluate) {
        return studentEvaluateService.updateById(studentEvaluate);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return studentEvaluateService.removeById(id);
    }

}
