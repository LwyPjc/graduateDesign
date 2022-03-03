package com.graduation.warning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.graduation.warning.entity.CollectInfo;
import com.graduation.warning.service.CollectInfoService;

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
 * @date 2022-3-3
 */
@RestController
@RequestMapping("/collectInfo")
public class CollectInfoController {

    @Autowired
    private CollectInfoService collectInfoService;

    @GetMapping("/findList")
    public List<CollectInfo> findList(CollectInfo collectInfo) {
        return collectInfoService.findList(collectInfo);
    }

    @GetMapping("/findListByPage")
    public Page<CollectInfo> findListByPage(CollectInfo collectInfo, Page page) {
        QueryWrapper<CollectInfo> queryWrapper = new QueryWrapper<>(collectInfo);
        return collectInfoService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public CollectInfo getById(@PathVariable String id) {
        return collectInfoService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(CollectInfo collectInfo) {
        collectInfoService.save(collectInfo);
        return collectInfo.getId();
    }

    @PostMapping("/edit")
    public boolean edit(CollectInfo collectInfo) {
        return collectInfoService.updateById(collectInfo);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return collectInfoService.removeById(id);
    }

}
