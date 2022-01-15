package com.graduation.warning.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import com.graduation.warning.entity.Class;
import com.graduation.warning.entity.Teacher;
import com.graduation.warning.entity.SysUser;
import com.graduation.warning.entity.dto.TeacherDto;
import com.graduation.warning.service.ClassService;
import com.graduation.warning.service.TeacherService;
import com.graduation.warning.service.SysUserService;
import com.graduation.warning.util.Constant;
import com.graduation.warning.util.ResultMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 教师信息 前端控制器
 * </p>
 *
 * @author auth
 * @since 2022-02-19
 */
@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ClassService classService;
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/findList")
    public List<TeacherDto> findList(Teacher eduTeacher) {
        List<TeacherDto> dtos = Lists.newArrayList();
        List<Teacher> teachers = teacherService.findList(eduTeacher);
        if (CollectionUtils.isEmpty(teachers)) {
            return dtos;
        }
        for (Teacher teacher : teachers) {
            TeacherDto dto = new TeacherDto();
            BeanUtil.copyProperties(teacher, dto);
            dtos.add(dto);
            if (StringUtils.isNotEmpty(teacher.getRelateClassIds())) {
                dto.setRelateClassIdList(Arrays.stream(teacher.getRelateClassIds().split(",")).map(x -> Integer.valueOf(x)).collect(Collectors.toList()));
            }
        }
        return dtos;
    }


    @GetMapping("/findListByPage")
    public Page<TeacherDto> findListByPage(Teacher teacher, Page page) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>(teacher);
        Page<TeacherDto> eduTeacherDtoPage = teacherService.page(page, queryWrapper);
        List<TeacherDto> eduTeacherDtos = Lists.newArrayList();
        //取出班级信息
        List<Class> aClasses = classService.findList(new Class());
        //构建一个map，key是班级id，value是班级名
        Map<Integer, String> idAndName = aClasses.stream().collect(Collectors.toMap(Class::getId,
                x -> x.getGrade() + "-" + x.getCollege() + "-" + x.getSubject() + "-" + x.getClassNo(), (x1, x2) -> x1));
        //将分页中的数据都取出来，将其中的班级id转换成班级的消息信息（学院 年级 专业）
        for (int i = 0; i < eduTeacherDtoPage.getRecords().size(); i++) {
            TeacherDto dto = new TeacherDto();
            BeanUtil.copyProperties(eduTeacherDtoPage.getRecords().get(i), dto, true);
            if (StringUtils.isNotEmpty(dto.getRelateClassIds())) {
                dto.setRelateClassIdList(Arrays.stream(dto.getRelateClassIds().split(",")).map(x -> Integer.valueOf(x)).collect(Collectors.toList()));
                List<String> classNames = Lists.newArrayList();
                for (Integer classId : dto.getRelateClassIdList()) {
                    classNames.add(idAndName.get(classId));
                }
                dto.setRelateClassName(CollectionUtil.join(classNames, "  |  \n\r"));
            }
            eduTeacherDtos.add(dto);
        }
        eduTeacherDtoPage.setRecords(eduTeacherDtos);


        return eduTeacherDtoPage;
    }

    @GetMapping("/{id}")
    public Teacher getById(@PathVariable String id) {
        return teacherService.getById(id);
    }

    @PostMapping("/save")
    public ResultMap save(Teacher teacher) {
        ResultMap map = new ResultMap();
        if (teacherService.findByCode(teacher.getCode()) != null) {
            map.setError("工号已存在，请重新输入");
            return map;
        }
        teacherService.save(teacher);
        //新增学生信息之后也要新增用户信息，这样学生才能登录
        SysUser user = new SysUser();
        //学号就是用户名
        user.setUsername(teacher.getCode());
        user.setPassword(Constant.DEFAULT_PASSWORD);
        user.setRole("2");
        sysUserService.save(user);
        map.setSuccss("操作成功");
        return map;
    }

    @PostMapping("/edit")
    public boolean edit(Teacher teacher) {
        return teacherService.updateById(teacher);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        //删除教师信息的同时也要删除用户信息
        Teacher teacher = teacherService.getById(id);
        //根据工号删除用户信息
        sysUserService.removeByUserName(teacher.getCode());
        return teacherService.removeById(id);
    }

}

