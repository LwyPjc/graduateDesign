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
@RequestMapping("/scoreEvaluate")
@CrossOrigin
public class ScoreEvaluateController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private OpenCourseService openCourseService;

    @Autowired
    private ScoreEvaluateService scoreEvaluateService;

    @Autowired
    private StudentStatisticsService studentStatisticsService;

    @GetMapping("/findList")
    public List<ScoreEvaluate> findList(ScoreEvaluate scoreEvaluate) {
        return scoreEvaluateService.findList(scoreEvaluate);
    }

    @GetMapping("/findListByPage")
    public Page<ScoreEvaluate> findListByPage(ScoreEvaluate scoreEvaluate, Page page) {
        QueryWrapper<ScoreEvaluate> queryWrapper = new QueryWrapper<>(scoreEvaluate);
        queryWrapper.eq(Constant.TEACHER_NAME, scoreEvaluate.getTeacherName());
        return scoreEvaluateService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public ScoreEvaluate getById(@PathVariable String id) {
        return scoreEvaluateService.getById(id);
    }


    @PostMapping("/save")
    public ResultMap save(ScoreEvaluate scoreEvaluate) {
        // 校验同一个学生老师类型的记录是否存在
        ResultMap resultMap = new ResultMap();
        Integer openCourseId = scoreEvaluate.getOpenCourseId();
        Integer studentId = scoreEvaluate.getStudentId();
        // 查询课程
        OpenCourse byId = openCourseService.getById(openCourseId);
        Integer classId = byId.getCourseId();
        Course course = courseService.getById(classId);
        // 查询学生
        Student student = studentService.getById(studentId);
        scoreEvaluate.setStudentName(student.getStuName());
        scoreEvaluate.setCourseName(course.getName());
        QueryWrapper<ScoreEvaluate> queryWrapper = new QueryWrapper<>(scoreEvaluate);
        // 校验 课程id 教师 学生
        queryWrapper.eq(Constant.OPEN_COURSE_ID, scoreEvaluate.getOpenCourseId());
        queryWrapper.eq(Constant.TEACHER_NAME, scoreEvaluate.getTeacherName());
        queryWrapper.eq(Constant.STU_ID, scoreEvaluate.getStudentId());
        int count = scoreEvaluateService.count(queryWrapper);
        if (count > 0) {
            // 提示用户
            return resultMap.setError("此纪录已存在，请使用编辑操作!");
        }

        scoreEvaluateService.save(scoreEvaluate);
        // 发送统计值
        studentStatisticsService.handleScore(scoreEvaluate);
        return resultMap;
    }

    @PostMapping("/edit")
    public boolean edit(ScoreEvaluate scoreEvaluate) {
        studentStatisticsService.handleScore(scoreEvaluate);
        return scoreEvaluateService.updateById(scoreEvaluate);
    }


    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return scoreEvaluateService.removeById(id);
    }

}
