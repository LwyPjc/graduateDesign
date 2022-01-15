package com.graduation.warning.controller;


import com.graduation.warning.entity.Classroom;
import com.graduation.warning.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 教室信息 前端控制器
 * </p>
 *
 * @author auth
 * @since 2022-02-19
 */
@Controller
@RequestMapping("/classRoom")
@ResponseBody
@CrossOrigin
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;

    @GetMapping("/findList")
    public List<Classroom> findList(Classroom classroom) {
        return classroomService.findList(classroom);
    }


    @GetMapping("/findListByPage")
    public Page<Classroom> findListByPage(Classroom classroom, Page page) {
        QueryWrapper<Classroom> queryWrapper = new QueryWrapper<>(classroom);
        return classroomService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public Classroom getById(@PathVariable String id) {
        return classroomService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(Classroom classroom) {
        classroomService.save(classroom);
        return classroom.getId();
    }

    @PostMapping("/edit")
    public boolean edit(Classroom classroom) {
        return classroomService.updateById(classroom);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return classroomService.removeById(id);
    }

}

