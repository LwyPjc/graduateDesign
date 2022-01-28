package com.graduation.warning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.warning.util.ResultMap;
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
    public ResultMap save(WarningRule warningRule) {
        return getResultMap(warningRule, true);
    }

    private ResultMap getResultMap(WarningRule warningRule, boolean isSave) {
        // 一样的预警等级只保存一条
        ResultMap resultMap = new ResultMap();
        QueryWrapper<WarningRule> queryWrapper = new QueryWrapper<>(warningRule);
        queryWrapper.eq("type", warningRule.getType());
        queryWrapper.eq("warning_level", warningRule.getWarningLevel());
        WarningRule one = warningRuleService.getOne(queryWrapper);
        if (isSave) {
            warningRuleService.save(warningRule);
            if (one != null) {
                return resultMap.setError("此纪录已存在，请更新!");
            }
        } else {
            if (!one.getId().equals(warningRule.getId())) {
                return resultMap.setError("此纪录已存在，请更新!");
            }
            warningRuleService.updateById(warningRule);
        }
        return resultMap.setSuccss("保存成功");
    }

    @PostMapping("/edit")
    public ResultMap edit(WarningRule warningRule) {
        return getResultMap(warningRule, false);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return warningRuleService.removeById(id);
    }

}
