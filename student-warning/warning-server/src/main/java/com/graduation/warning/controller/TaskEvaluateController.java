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
@RequestMapping("/taskEvaluate")
@CrossOrigin
public class TaskEvaluateController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private OpenCourseService openCourseService;

    @Autowired
    private TaskEvaluateService taskEvaluateService;

    @Autowired
    private StudentStatisticsService studentStatisticsService;

    @GetMapping("/findList")
    public List<TaskEvaluate> findList(TaskEvaluate taskEvaluate) {
        return taskEvaluateService.findList(taskEvaluate);
    }

    @GetMapping("/findListByPage")
    public Page<TaskEvaluate> findListByPage(TaskEvaluate taskEvaluate, Page page) {
        QueryWrapper<TaskEvaluate> queryWrapper = new QueryWrapper<>(taskEvaluate);
        return taskEvaluateService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public TaskEvaluate getById(@PathVariable String id) {
        return taskEvaluateService.getById(id);
    }

    @PostMapping("/save")
    public ResultMap save(TaskEvaluate taskEvaluate) {
        // 校验是否唯一
        ResultMap resultMap = new ResultMap();
        Integer studentId = taskEvaluate.getStudentId();
        Student student = studentService.getById(studentId);
        taskEvaluate.setStudentName(student.getStuName());
        QueryWrapper<TaskEvaluate> queryWrapper = new QueryWrapper<>(taskEvaluate);
        // 课程 教师 学生 校验唯一
        queryWrapper.eq(Constant.COURSE_NAME, taskEvaluate.getCourseName());
        queryWrapper.eq(Constant.TEACHER_NAME, taskEvaluate.getTeacherName());
        queryWrapper.eq(Constant.STU_ID, taskEvaluate.getStudentId());
        int count = taskEvaluateService.count(queryWrapper);
        if (count > 0) {
            return resultMap.setError("此纪录已存在，请使用编辑操作!");
        }

        taskEvaluateService.save(taskEvaluate);
        // 发送学生统计记录
        studentStatisticsService.handleTask(taskEvaluate);
        return resultMap;
    }

    @PostMapping("/edit")
    public boolean edit(TaskEvaluate taskEvaluate) {
        studentStatisticsService.handleTask(taskEvaluate);
        return taskEvaluateService.updateById(taskEvaluate);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return taskEvaluateService.removeById(id);
    }

}
