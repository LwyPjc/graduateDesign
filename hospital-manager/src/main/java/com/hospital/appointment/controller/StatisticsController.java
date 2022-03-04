package com.hospital.appointment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital.appointment.entity.Statistics;
import com.hospital.appointment.entity.dto.StatisticsDto;
import com.hospital.appointment.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.Serializable;
import java.util.List;

/**
 * <p>标题: 统计 - 请求控制层</p>
 *
 * @version: 1.0
 * @author: mic
 * @date 2022-3-3
 */
@RestController
@RequestMapping("/statistics")
@CrossOrigin
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/findList")
    public List<Statistics> findList(Statistics statistics) {
        return statisticsService.findList(statistics);
    }

    @GetMapping("/findListByPage")
    public Page<Statistics> findListByPage(StatisticsDto statistics, Page page) {
        QueryWrapper<Statistics> queryWrapper = new QueryWrapper<Statistics>(statistics);
        if (statistics.getDateStart() != null && statistics.getDateEnd() != null) {
            queryWrapper.gt("create_time", statistics.getDateStartWithDate());
            queryWrapper.le("create_time", statistics.getDateEndWithDate());
        }
        Page result = statisticsService.page(page, queryWrapper);
        return result;
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
