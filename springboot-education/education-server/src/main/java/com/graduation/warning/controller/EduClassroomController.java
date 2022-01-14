package com.graduation.warning.controller;


import com.graduation.warning.entity.EduClassroom;
import com.graduation.warning.service.EduClassroomService;
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
 * @since 2021-03-29
 */
@Controller
@RequestMapping("/eduClassRoom")
@ResponseBody
@CrossOrigin
public class EduClassroomController {
    @Autowired
    private EduClassroomService eduClassroomService;

    @GetMapping("/findList")
    public List<EduClassroom> findList(EduClassroom eduClassroom) {
        return eduClassroomService.findList(eduClassroom);
    }


    @GetMapping("/findListByPage")
    public Page<EduClassroom> findListByPage(EduClassroom eduClassroom, Page page) {
        QueryWrapper<EduClassroom> queryWrapper = new QueryWrapper<>(eduClassroom);
        return eduClassroomService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public EduClassroom getById(@PathVariable String id) {
        return eduClassroomService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(EduClassroom eduClassroom) {
        eduClassroomService.save(eduClassroom);
        return eduClassroom.getId();
    }

    @PostMapping("/edit")
    public boolean edit(EduClassroom eduClassroom) {
        return eduClassroomService.updateById(eduClassroom);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return eduClassroomService.removeById(id);
    }

}

