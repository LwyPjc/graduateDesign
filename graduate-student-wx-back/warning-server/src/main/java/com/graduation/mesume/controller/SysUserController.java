package com.graduation.mesume.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.mesume.entity.SysUser;
import com.graduation.mesume.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author auth
 * @since 2021-01-27
 */
@Controller
@RequestMapping("/sysUser")
@ResponseBody
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

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return sysUserService.removeById(id);
    }

    /**
     * 更新微信小程序登录的用户的昵称
     * @param openid
     * @param nickName
     * @return
     */
    @GetMapping("/updateName")
    public boolean updateName(String openid,String nickName) {
        if(StringUtils.isEmpty(nickName)){
            return false;
        }
        sysUserService.updateNameByUserName(openid,nickName);
        return true;
    }

}

