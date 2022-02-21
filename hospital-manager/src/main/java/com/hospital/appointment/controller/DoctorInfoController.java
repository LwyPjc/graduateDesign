package com.hospital.appointment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital.appointment.entity.DoctorInfo;
import com.hospital.appointment.entity.SysUser;
import com.hospital.appointment.service.DoctorInfoService;
import com.hospital.appointment.service.SysUserService;
import com.hospital.appointment.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
 * @date 2022-2-21
 */
@RestController
@RequestMapping("/doctorInfo")
@CrossOrigin
public class DoctorInfoController {

    @Autowired
    private DoctorInfoService doctorInfoService;

    @Autowired
    private SysUserService sysUserService;

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
        // 添加医生登录信息
        SysUser sysUser = new SysUser();
        sysUser.setUsername(doctorInfo.getPhone());
        sysUser.setPassword(Constant.DEFAULT_PASSWORD);
        sysUser.setRole("1");
        sysUserService.save(sysUser);
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
