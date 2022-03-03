package com.graduation.warning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.graduation.warning.entity.TitleInfo;
import com.graduation.warning.service.TitleInfoService;

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
@RequestMapping("/titleInfo")
public class TitleInfoController {

    @Autowired
    private TitleInfoService titleInfoService;

    @GetMapping("/findList")
    public List<TitleInfo> findList(TitleInfo titleInfo) {
        return titleInfoService.findList(titleInfo);
    }

    @GetMapping("/findListByPage")
    public Page<TitleInfo> findListByPage(TitleInfo titleInfo, Page page) {
        QueryWrapper<TitleInfo> queryWrapper = new QueryWrapper<>(titleInfo);
        return titleInfoService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public TitleInfo getById(@PathVariable String id) {
        return titleInfoService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(TitleInfo titleInfo) {
        titleInfoService.save(titleInfo);
        return titleInfo.getId();
    }

    @PostMapping("/edit")
    public boolean edit(TitleInfo titleInfo) {
        return titleInfoService.updateById(titleInfo);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return titleInfoService.removeById(id);
    }

}
