package com.graduation.warning.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.graduation.warning.entity.Class;
import com.graduation.warning.entity.Teacher;
import com.graduation.warning.entity.dto.ClassDto;
import com.graduation.warning.service.ClassService;
import com.graduation.warning.service.TeacherService;
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
 * @since 2022-02-19
 */
@Controller
@RequestMapping("/eduClass")
@ResponseBody
@CrossOrigin
public class ClassController {

    @Autowired
    private ClassService classService;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/findList")
    public List<ClassDto> findList(Class aClass) {
        List<Class> aClasses = classService.findList(aClass);
        List<ClassDto> result = Lists.newArrayList();
        for (Class class1 : aClasses) {
            ClassDto dto = new ClassDto();
            BeanUtil.copyProperties(class1, dto, true);
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
    public List<ClassDto> findWithoutTeacherClass() {
        List<Class> aClasses = classService.findWithoutTeacherClass();
        List<ClassDto> result = Lists.newArrayList();
        for (Class class1 : aClasses) {
            ClassDto dto = new ClassDto();
            BeanUtil.copyProperties(class1, dto, true);
            result.add(dto);
        }
        //将教师id转换为教师名称
        transferTeacherId(result);
        return result;
    }

    @GetMapping("/findListByPage")
    public Page<Class> findListByPage(Class aClass, Page page) {
        QueryWrapper<Class> queryWrapper = new QueryWrapper<>(aClass);
        return classService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public Class getById(@PathVariable String id) {
        return classService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(Class aClass) {
        classService.save(aClass);
        return aClass.getId();
    }

    @PostMapping("/edit")
    public boolean edit(Class aClass) {
        return classService.updateById(aClass);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return classService.removeById(id);
    }

    /**
     * 将教师id转化为教师名称
     */
    private void transferTeacherId(List<ClassDto> classes) {
        //获取所有教师
        List<Teacher> teachers = teacherService.list();
        //  班级id   教师名
        Map<Integer, String> idAndName = teachers.stream().collect(Collectors.toMap(Teacher::getId, Teacher::getName, (x1, x2) -> x1));
        for (ClassDto dto : classes) {
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

