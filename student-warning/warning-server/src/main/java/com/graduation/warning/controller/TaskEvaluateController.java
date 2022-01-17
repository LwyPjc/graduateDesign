package com.graduation.warning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.graduation.warning.entity.TaskEvaluate;
import com.graduation.warning.service.TaskEvaluateService;

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
    private TaskEvaluateService taskEvaluateService;

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
    public Serializable save(TaskEvaluate taskEvaluate) {
        taskEvaluateService.save(taskEvaluate);
        return taskEvaluate.getId();
    }

    @PostMapping("/edit")
    public boolean edit(TaskEvaluate taskEvaluate) {
        return taskEvaluateService.updateById(taskEvaluate);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return taskEvaluateService.removeById(id);
    }

}
