package com.graduation.warning.controller;


import com.graduation.warning.entity.Course;
import com.graduation.warning.service.CourseService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 课表信息 前端控制器
 * </p>
 *
 * @author auth
 * @since 2022-02-19
 */
@Controller
@RequestMapping("/course")
@ResponseBody
@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/findList")
    public List<Course> findList(Course course) {
        return courseService.findList(course);
    }

    @GetMapping("/findListByPage")
    public Page<Course> findListByPage(Course course, Page page) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>(course);
        return courseService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public Course getById(@PathVariable String id) {
        return courseService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(Course course) {
        courseService.save(course);
        return course.getId();
    }


    @PostMapping("/edit")
    public boolean edit(Course course) {
        return courseService.updateById(course);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return courseService.removeById(id);
    }
}

