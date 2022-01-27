package com.graduation.warning.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.graduation.warning.entity.Student;
import com.graduation.warning.entity.dto.StudentStatisticsDto;
import com.graduation.warning.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.graduation.warning.entity.StudentStatistics;
import com.graduation.warning.service.StudentStatisticsService;

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
 * @date 2022-1-20
 */
@RestController
@RequestMapping("/studentStatistics")
@CrossOrigin
public class StudentStatisticsController {

    @Autowired
    private StudentStatisticsService studentStatisticsService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/findList")
    public List<StudentStatistics> findList(StudentStatistics studentStatistics) {
        return studentStatisticsService.findList(studentStatistics);
    }

    @GetMapping("/findListByPage")
    public Page<Object> findListByPage(StudentStatistics studentStatistics, Page page) {
        QueryWrapper<StudentStatistics> queryWrapper = new QueryWrapper<>(studentStatistics);
        Page<Object> result = studentStatisticsService.page(page, queryWrapper);
        List<Object> records = result.getRecords();
        if (!CollectionUtils.isEmpty(records)) {
            for (int i = 0; i < records.size(); i++) {
                // 转换 添加学生家庭电话
                Object record = records.get(i);
                StudentStatistics statistics = (StudentStatistics) record;
                Integer studentId = statistics.getStudentId();
                Student byId = studentService.getById(studentId);
                StudentStatisticsDto studentStatisticsDto = new StudentStatisticsDto();
                BeanUtil.copyProperties(statistics, studentStatisticsDto);
                studentStatisticsDto.setFamilyPhone(byId.getHomePhone());
                records.set(i, studentStatisticsDto);
            }
        }
        return result;
    }

    @GetMapping("/{id}")
    public StudentStatistics getById(@PathVariable String id) {
        return studentStatisticsService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(StudentStatistics studentStatistics) {
        studentStatisticsService.save(studentStatistics);
        return studentStatistics.getId();
    }

    @PostMapping("/edit")
    public boolean edit(StudentStatistics studentStatistics) {
        return studentStatisticsService.updateById(studentStatistics);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return studentStatisticsService.removeById(id);
    }

}
