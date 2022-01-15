package com.graduation.warning.controller;


import com.graduation.warning.entity.SysUser;
import com.graduation.warning.service.SysUserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author auth
 * @since 2022-02-19
 */
@RestController
@RequestMapping("/sysUser")
@CrossOrigin
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

    /**
     * 修改密码
     * @return
     */
    @PostMapping("/editPassword")
    public boolean editPassword(String stuCode,String password) {
        return sysUserService.updatePasswordByUserName(stuCode,password);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return sysUserService.removeById(id);
    }

}

