package com.graduation.warning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.graduation.warning.entity.Statistics;
import com.graduation.warning.service.StatisticsService;

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
 * @date 2022-2-21
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/findList")
    public List<Statistics> findList(Statistics statistics) {
        return statisticsService.findList(statistics);
    }

    @GetMapping("/findListByPage")
    public Page<Statistics> findListByPage(Statistics statistics, Page page) {
        QueryWrapper<Statistics> queryWrapper = new QueryWrapper<>(statistics);
        return statisticsService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public Statistics getById(@PathVariable String id) {
        return statisticsService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(Statistics statistics) {
        statisticsService.save(statistics);
        return statistics.getId();
    }

    @PostMapping("/edit")
    public boolean edit(Statistics statistics) {
        return statisticsService.updateById(statistics);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return statisticsService.removeById(id);
    }

}
