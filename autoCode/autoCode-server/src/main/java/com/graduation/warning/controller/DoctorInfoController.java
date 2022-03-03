package com.graduation.warning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.graduation.warning.entity.DoctorInfo;
import com.graduation.warning.service.DoctorInfoService;

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
 * @date 2022-3-3
 */
@RestController
@RequestMapping("/doctorInfo")
public class DoctorInfoController {

    @Autowired
    private DoctorInfoService doctorInfoService;

    @GetMapping("/findList")
    public List<DoctorInfo> findList(DoctorInfo doctorInfo) {
        return doctorInfoService.findList(doctorInfo);
    }

    @GetMapping("/findListByPage")
    public Page<DoctorInfo> findListByPage(DoctorInfo doctorInfo, Page page) {
        QueryWrapper<DoctorInfo> queryWrapper = new QueryWrapper<>(doctorInfo);
        return doctorInfoService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public DoctorInfo getById(@PathVariable String id) {
        return doctorInfoService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(DoctorInfo doctorInfo) {
        doctorInfoService.save(doctorInfo);
        return doctorInfo.getId();
    }

    @PostMapping("/edit")
    public boolean edit(DoctorInfo doctorInfo) {
        return doctorInfoService.updateById(doctorInfo);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return doctorInfoService.removeById(id);
    }

}
