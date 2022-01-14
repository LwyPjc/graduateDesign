package com.graduation.mesume.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Controller;
import com.graduation.mesume.entity.SysCategory;
import com.graduation.mesume.service.SysCategoryService;

/**
 * <p>
 * 物品类别 前端控制器
 * </p>
 *
 * @author auth
 * @since 2021-02-01
 */
@Controller
@RequestMapping("/sysCategory")
@CrossOrigin
@ResponseBody
public class SysCategoryController {
    @Autowired
    private SysCategoryService sysCategoryService;

    @GetMapping("/findList")
    public List<SysCategory> findList(SysCategory sysCategory) {
        return sysCategoryService.findList(sysCategory);
    }

    @GetMapping("/findListByPage")
    public Page<SysCategory> findListByPage(SysCategory sysCategory, Page page) {
        QueryWrapper<SysCategory> queryWrapper = new QueryWrapper<>(sysCategory);
        return sysCategoryService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public SysCategory getById(@PathVariable String id) {
        return sysCategoryService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(SysCategory sysCategory) {
        sysCategoryService.save(sysCategory);
        return sysCategory.getId();
    }

    @PostMapping("/edit")
    public boolean edit(SysCategory sysCategory) {
        return sysCategoryService.updateById(sysCategory);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return sysCategoryService.removeById(id);
    }
}

