package com.hospital.appointment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hospital.appointment.entity.Department;
import com.hospital.appointment.service.DepartmentService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>标题: 科室 - 请求控制层</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: kknight
 * @date 2022-2-20
 */
@RestController
@RequestMapping("/department")
@CrossOrigin
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/findAll")
    public List<Department> findAll(){
        return departmentService.findAll();
    }


    @GetMapping("/findList")
    public List<Department> findList(Department department) {
        return departmentService.findList(department);
    }

    @GetMapping("/findListByPage")
    public Page<Department> findListByPage(Department department, Page page) {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>(department);
        return departmentService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public Department getById(@PathVariable String id) {
        return departmentService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(Department department) {
        department.setCreateTime(new Date());
        departmentService.save(department);
        return department.getId();
    }

    @PostMapping("/edit")
    public boolean edit(Department department) {
        return departmentService.updateById(department);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return departmentService.removeById(id);
    }

}
