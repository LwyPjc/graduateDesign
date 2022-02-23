package com.graduation.warning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.graduation.warning.entity.SysUser;
import com.graduation.warning.service.SysUserService;

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
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/findList")
    public List<SysUser> findList(SysUser sysUser) {
        return sysUserService.findList(sysUser);
    }

    @GetMapping("/findListByPage")
    public Page<SysUser> findListByPage(SysUser sysUser, Page page) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>(sysUser);
        return sysUserService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public SysUser getById(@PathVariable String id) {
        return sysUserService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(SysUser sysUser) {
        sysUserService.save(sysUser);
        return sysUser.getId();
    }

    @PostMapping("/edit")
    public boolean edit(SysUser sysUser) {
        return sysUserService.updateById(sysUser);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return sysUserService.removeById(id);
    }

}
