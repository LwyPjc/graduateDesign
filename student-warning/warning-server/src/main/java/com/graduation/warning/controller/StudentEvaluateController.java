package com.graduation.warning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.warning.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.graduation.warning.entity.StudentEvaluate;
import com.graduation.warning.service.StudentEvaluateService;

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
    private StudentEvaluateService studentEvaluateService;

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
    public Serializable save(StudentEvaluate studentEvaluate) {
        studentEvaluateService.save(studentEvaluate);
        return studentEvaluate.getId();
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
