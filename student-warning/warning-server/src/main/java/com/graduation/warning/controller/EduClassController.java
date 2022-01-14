package com.graduation.warning.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.graduation.warning.entity.EduClass;
import com.graduation.warning.entity.EduTeacher;
import com.graduation.warning.entity.dto.EduClassDto;
import com.graduation.warning.service.EduClassService;
import com.graduation.warning.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.beans.PropertyEditorSupport;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 班级信息 前端控制器
 * </p>
 *
 * @author auth
 * @since 2021-03-29
 */
@Controller
@RequestMapping("/eduClass")
@ResponseBody
@CrossOrigin
public class EduClassController {

    @Autowired
    private EduClassService eduClassService;
    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("/findList")
    public List<EduClassDto> findList(EduClass eduClass) {
        List<EduClass> eduClasses = eduClassService.findList(eduClass);
        List<EduClassDto> result = Lists.newArrayList();
        for (EduClass eduClass1 : eduClasses) {
            EduClassDto dto = new EduClassDto();
            BeanUtil.copyProperties(eduClass1, dto, true);
            result.add(dto);
        }
        //将教师id转换为教师名称
        transferTeacherId(result);
        return result;
    }

    /**
     * 查询未关联教师的班级
     * @return
     */
    @GetMapping("/findWithoutTeacherClass")
    public List<EduClassDto> findWithoutTeacherClass() {
        List<EduClass> eduClasses = eduClassService.findWithoutTeacherClass();
        List<EduClassDto> result = Lists.newArrayList();
        for (EduClass eduClass1 : eduClasses) {
            EduClassDto dto = new EduClassDto();
            BeanUtil.copyProperties(eduClass1, dto, true);
            result.add(dto);
        }
        //将教师id转换为教师名称
        transferTeacherId(result);
        return result;
    }

    @GetMapping("/findListByPage")
    public Page<EduClass> findListByPage(EduClass eduClass, Page page) {
        QueryWrapper<EduClass> queryWrapper = new QueryWrapper<>(eduClass);
        return eduClassService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public EduClass getById(@PathVariable String id) {
        return eduClassService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(EduClass eduClass) {
        eduClassService.save(eduClass);
        return eduClass.getId();
    }

    @PostMapping("/edit")
    public boolean edit(EduClass eduClass) {
        return eduClassService.updateById(eduClass);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return eduClassService.removeById(id);
    }

    /**
     * 将教师id转化为教师名称
     */
    private void transferTeacherId(List<EduClassDto> classes) {
        //获取所有教师
        List<EduTeacher> eduTeachers = eduTeacherService.list();
        //  班级id   教师名
        Map<Integer, String> idAndName = eduTeachers.stream().collect(Collectors.toMap(EduTeacher::getId, EduTeacher::getName, (x1, x2) -> x1));
        for (EduClassDto dto : classes) {
            //设置教师名
            dto.setCounselorName(idAndName.getOrDefault(dto.getCounselorId(), ""));
        }
    }

    @InitBinder
    public void initBinder(final WebDataBinder webdataBinder) {
        webdataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(new Date(Long.valueOf(text)));
            }
        });
    }
}

