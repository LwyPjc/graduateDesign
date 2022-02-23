package com.graduation.warning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.graduation.warning.entity.StudentStatistics;
import com.graduation.warning.service.StudentStatisticsService;

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
 * @date 2022-1-20
 */
@RestController
@RequestMapping("/studentStatistics")
public class StudentStatisticsController {

    @Autowired
    private StudentStatisticsService studentStatisticsService;

    @GetMapping("/findList")
    public List<StudentStatistics> findList(StudentStatistics studentStatistics) {
        return studentStatisticsService.findList(studentStatistics);
    }

    @GetMapping("/findListByPage")
    public Page<StudentStatistics> findListByPage(StudentStatistics studentStatistics, Page page) {
        QueryWrapper<StudentStatistics> queryWrapper = new QueryWrapper<>(studentStatistics);
        return studentStatisticsService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public StudentStatistics getById(@PathVariable String id) {
        return studentStatisticsService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(StudentStatistics studentStatistics) {
        studentStatisticsService.save(studentStatistics);
        return studentStatistics.getId();
    }

    @PostMapping("/edit")
    public boolean edit(StudentStatistics studentStatistics) {
        return studentStatisticsService.updateById(studentStatistics);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return studentStatisticsService.removeById(id);
    }

}
