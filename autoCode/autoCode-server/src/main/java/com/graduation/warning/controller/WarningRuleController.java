package com.graduation.warning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zoe.optimus.core.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
 * @date 2022-1-16
 */
@RestController
@RequestMapping("/warningRule")
@Api(value = "WarningRuleController", tags = {"服务"})
public class WarningRuleController extends BaseController {

    @Autowired
    private WarningRuleService warningRuleService;

    @ApiOperation(value = "获取列表")
    @GetMapping("/findList")
    public List<WarningRule> findList(WarningRule warningRule) {
        return warningRuleService.findList(warningRule);
    }

    @ApiOperation(value = "获取列表（分页）", response = Page.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "current", value = "当前页码", required = true, dataType = "int"),
    })
    @GetMapping("/findListByPage")
    public Page<WarningRule> findListByPage(WarningRule warningRule, Page page) {
        QueryWrapper<WarningRule> queryWrapper = new QueryWrapper<>(warningRule);
        return warningRuleService.page(page, queryWrapper);
    }

    @ApiOperation(value = "获取详细信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "的ID", required = true, dataType = "String")
    @GetMapping("/{id}")
    public WarningRule getById(@PathVariable String id) {
        return warningRuleService.getById(id);
    }

    @ApiOperation(value = "创建")
    @PostMapping("/save")
    public Serializable save(WarningRule warningRule) {
        warningRuleService.save(warningRule);
        return warningRule.getId();
    }

    @ApiOperation(value = "更新详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "ID", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping("/edit")
    public boolean edit(WarningRule warningRule) {
        return warningRuleService.updateById(warningRule);
    }

    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParam(name = "id", value = "ID", required = true, paramType = "query", dataType = "String")
    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return warningRuleService.removeById(id);
    }

}
