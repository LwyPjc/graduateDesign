package com.hospital.appointment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hospital.appointment.entity.HospitalInfo;
import com.hospital.appointment.service.HospitalInfoService;

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
 * @date 2022-2-14
 */
@RestController
@RequestMapping("/hospitalInfo")
public class HospitalInfoController {

    @Autowired
    private HospitalInfoService hospitalInfoService;

    @GetMapping("/findList")
    public List<HospitalInfo> findList(HospitalInfo hospitalInfo) {
        return hospitalInfoService.findList(hospitalInfo);
    }

    @GetMapping("/findListByPage")
    public Page<HospitalInfo> findListByPage(HospitalInfo hospitalInfo, Page page) {
        QueryWrapper<HospitalInfo> queryWrapper = new QueryWrapper<>(hospitalInfo);
        return hospitalInfoService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public HospitalInfo getById(@PathVariable String id) {
        return hospitalInfoService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(HospitalInfo hospitalInfo) {
        hospitalInfoService.save(hospitalInfo);
        return hospitalInfo.getId();
    }

    @PostMapping("/edit")
    public boolean edit(HospitalInfo hospitalInfo) {
        return hospitalInfoService.updateById(hospitalInfo);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return hospitalInfoService.removeById(id);
    }

}
