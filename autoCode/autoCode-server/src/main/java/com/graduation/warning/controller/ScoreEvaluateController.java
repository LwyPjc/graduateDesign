package com.graduation.warning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.graduation.warning.entity.ScoreEvaluate;
import com.graduation.warning.service.ScoreEvaluateService;

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
public class ScoreEvaluateController {

    @Autowired
    private ScoreEvaluateService scoreEvaluateService;

    @GetMapping("/findList")
    public List<ScoreEvaluate> findList(ScoreEvaluate scoreEvaluate) {
        return scoreEvaluateService.findList(scoreEvaluate);
    }

    @GetMapping("/findListByPage")
    public Page<ScoreEvaluate> findListByPage(ScoreEvaluate scoreEvaluate, Page page) {
        QueryWrapper<ScoreEvaluate> queryWrapper = new QueryWrapper<>(scoreEvaluate);
        return scoreEvaluateService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public ScoreEvaluate getById(@PathVariable String id) {
        return scoreEvaluateService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(ScoreEvaluate scoreEvaluate) {
        scoreEvaluateService.save(scoreEvaluate);
        return scoreEvaluate.getId();
    }

    @PostMapping("/edit")
    public boolean edit(ScoreEvaluate scoreEvaluate) {
        return scoreEvaluateService.updateById(scoreEvaluate);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return scoreEvaluateService.removeById(id);
    }

}
