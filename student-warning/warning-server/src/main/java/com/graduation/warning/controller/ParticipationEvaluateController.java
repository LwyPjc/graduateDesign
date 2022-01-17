package com.graduation.warning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.graduation.warning.entity.ParticipationEvaluate;
import com.graduation.warning.service.ParticipationEvaluateService;

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
@RequestMapping("/participationEvaluate")
public class ParticipationEvaluateController {

    @Autowired
    private ParticipationEvaluateService participationEvaluateService;

    @GetMapping("/findList")
    public List<ParticipationEvaluate> findList(ParticipationEvaluate participationEvaluate) {
        return participationEvaluateService.findList(participationEvaluate);
    }

    @GetMapping("/findListByPage")
    public Page<ParticipationEvaluate> findListByPage(ParticipationEvaluate participationEvaluate, Page page) {
        QueryWrapper<ParticipationEvaluate> queryWrapper = new QueryWrapper<>(participationEvaluate);
        return participationEvaluateService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public ParticipationEvaluate getById(@PathVariable String id) {
        return participationEvaluateService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(ParticipationEvaluate participationEvaluate) {
        participationEvaluateService.save(participationEvaluate);
        return participationEvaluate.getID();
    }

    @PostMapping("/edit")
    public boolean edit(ParticipationEvaluate participationEvaluate) {
        return participationEvaluateService.updateById(participationEvaluate);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return participationEvaluateService.removeById(id);
    }

}
