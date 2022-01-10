package com.graduation.education.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zoe.optimus.core.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.graduation.education.entity.EduCourse;
import com.graduation.education.service.EduCourseService;

import java.io.Serializable;
import java.util.List;

/**
 * <p>标题: 服务 - 请求控制层</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2021</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: shizh
 * @date 2021-3-29
 */
@RestController
@RequestMapping("/eduCourse")
@Api(value = "EduCourseController", tags = {"服务"})
public class EduCourseController extends BaseController {

    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation(value = "获取列表")
    @GetMapping("/findList")
    public List<EduCourse> findList(EduCourse eduCourse) {
        return eduCourseService.findList(eduCourse);
    }

    @ApiOperation(value = "获取列表（分页）", response = Page.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "current", value = "当前页码", required = true, dataType = "int"),
    })
    @GetMapping("/findListByPage")
    public Page<EduCourse> findListByPage(EduCourse eduCourse, Page page) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>(eduCourse);
        return eduCourseService.page(page, queryWrapper);
    }

    @ApiOperation(value = "获取详细信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "的ID", required = true, dataType = "String")
    @GetMapping("/{id}")
    public EduCourse getById(@PathVariable String id) {
        return eduCourseService.getById(id);
    }

    @ApiOperation(value = "创建")
    @PostMapping("/save")
    public Serializable save(EduCourse eduCourse) {
        eduCourseService.save(eduCourse);
        return eduCourse.getId();
    }

    @ApiOperation(value = "更新详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "ID", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping("/edit")
    public boolean edit(EduCourse eduCourse) {
        return eduCourseService.updateById(eduCourse);
    }

    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParam(name = "id", value = "ID", required = true, paramType = "query", dataType = "String")
    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return eduCourseService.removeById(id);
    }

}
