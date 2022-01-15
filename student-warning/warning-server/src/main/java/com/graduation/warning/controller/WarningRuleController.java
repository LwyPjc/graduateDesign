package com.graduation.warning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.graduation.warning.entity.WarningRule;
import com.graduation.warning.service.WarningRuleService;

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
 * @date 2022-1-15
 */
@RestController
@RequestMapping("/warningRule")
@CrossOrigin
public class WarningRuleController {

    @Autowired
    private WarningRuleService warningRuleService;

    @GetMapping("/findList")
    public List<WarningRule> findList(WarningRule warningRule) {
        return warningRuleService.findList(warningRule);
    }

    @GetMapping("/findListByPage")
    public Page<WarningRule> findListByPage(WarningRule warningRule, Page page) {
        QueryWrapper<WarningRule> queryWrapper = new QueryWrapper<>(warningRule);
        Page pageResult = warningRuleService.page(page, queryWrapper);
        return pageResult;
    }

    @GetMapping("/{id}")
    public WarningRule getById(@PathVariable String id) {
        return warningRuleService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(WarningRule warningRule) {
        warningRuleService.save(warningRule);
        return warningRule.getId();
    }

    @PostMapping("/edit")
    public boolean edit(WarningRule warningRule) {
        return warningRuleService.updateById(warningRule);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return warningRuleService.removeById(id);
    }

}
