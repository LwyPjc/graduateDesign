package com.graduation.education.controller;


import com.graduation.education.entity.EduCourse;
import com.graduation.education.service.EduCourseService;
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
 * @since 2021-03-29
 */
@Controller
@RequestMapping("/eduCourse")
@ResponseBody
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    @GetMapping("/findList")
    public List<EduCourse> findList(EduCourse eduCourse) {
        return eduCourseService.findList(eduCourse);
    }

    @GetMapping("/findListByPage")
    public Page<EduCourse> findListByPage(EduCourse eduCourse, Page page) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>(eduCourse);
        return eduCourseService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public EduCourse getById(@PathVariable String id) {
        return eduCourseService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(EduCourse eduCourse) {
        eduCourseService.save(eduCourse);
        return eduCourse.getId();
    }


    @PostMapping("/edit")
    public boolean edit(EduCourse eduCourse) {
        return eduCourseService.updateById(eduCourse);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return eduCourseService.removeById(id);
    }
}

