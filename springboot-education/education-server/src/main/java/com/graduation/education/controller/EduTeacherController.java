package com.graduation.education.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.graduation.education.entity.EduClass;
import com.graduation.education.entity.EduStudent;
import com.graduation.education.entity.EduTeacher;
import com.graduation.education.entity.SysUser;
import com.graduation.education.entity.dto.EduTeacherDto;
import com.graduation.education.service.EduClassService;
import com.graduation.education.service.EduTeacherService;
import com.graduation.education.service.SysUserService;
import com.graduation.education.util.Constant;
import com.graduation.education.util.ResultMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * @since 2021-03-29
 */
@RestController
@RequestMapping("/eduTeacher")
@CrossOrigin
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;
    @Autowired
    private EduClassService eduClassService;
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/findList")
    public List<EduTeacherDto> findList(EduTeacher eduTeacher) {
        List<EduTeacherDto> dtos = Lists.newArrayList();
        List<EduTeacher> teachers = eduTeacherService.findList(eduTeacher);
        if (CollectionUtils.isEmpty(teachers)) {
            return dtos;
        }
        for (EduTeacher teacher : teachers) {
            EduTeacherDto dto = new EduTeacherDto();
            BeanUtil.copyProperties(teacher, dto);
            if (StringUtils.isNotEmpty(teacher.getRelateClassIds())) {
                dto.setRelateClassIdList(Arrays.stream(teacher.getRelateClassIds().split(",")).map(x -> Integer.valueOf(x)).collect(Collectors.toList()));
            }
        }
        return dtos;
    }


    @GetMapping("/findListByPage")
    public Page<EduTeacherDto> findListByPage(EduTeacher eduTeacher, Page page) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>(eduTeacher);
        Page<EduTeacherDto> eduTeacherDtoPage = eduTeacherService.page(page, queryWrapper);
        List<EduTeacherDto> eduTeacherDtos = Lists.newArrayList();
        //取出班级信息
        List<EduClass> eduClasses = eduClassService.findList(new EduClass());
        //构建一个map，key是班级id，value是班级名
        Map<Integer, String> idAndName = eduClasses.stream().collect(Collectors.toMap(EduClass::getId,
                x -> x.getGrade() + "-" + x.getCollege() + "-" + x.getSubject() + "-" + x.getClassNo(), (x1, x2) -> x1));
        //将分页中的数据都取出来，将其中的班级id转换成班级的消息信息（学院 年级 专业）
        for (int i = 0; i < eduTeacherDtoPage.getRecords().size(); i++) {
            EduTeacherDto dto = new EduTeacherDto();
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
    public EduTeacher getById(@PathVariable String id) {
        return eduTeacherService.getById(id);
    }

    @PostMapping("/save")
    public ResultMap save(EduTeacher eduTeacher) {
        ResultMap map = new ResultMap();
        if (eduTeacherService.findByCode(eduTeacher.getCode()) != null) {
            map.setError("工号已存在，请重新输入");
            return map;
        }
        eduTeacherService.save(eduTeacher);
        //新增学生信息之后也要新增用户信息，这样学生才能登录
        SysUser user = new SysUser();
        //学号就是用户名
        user.setUsername(eduTeacher.getCode());
        user.setPassword(Constant.DEFAULT_PASSWORD);
        user.setRole("2");
        sysUserService.save(user);
        map.setSuccss("操作成功");
        return map;
    }

    @PostMapping("/edit")
    public boolean edit(EduTeacher eduTeacher) {
        return eduTeacherService.updateById(eduTeacher);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        //删除教师信息的同时也要删除用户信息
        EduTeacher teacher = eduTeacherService.getById(id);
        //根据工号删除用户信息
        sysUserService.removeByUserName(teacher.getCode());
        return eduTeacherService.removeById(id);
    }

}

