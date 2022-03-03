package com.graduation.warning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.graduation.warning.entity.UserInfo;
import com.graduation.warning.service.UserInfoService;

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
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/findList")
    public List<UserInfo> findList(UserInfo userInfo) {
        return userInfoService.findList(userInfo);
    }

    @GetMapping("/findListByPage")
    public Page<UserInfo> findListByPage(UserInfo userInfo, Page page) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>(userInfo);
        return userInfoService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public UserInfo getById(@PathVariable String id) {
        return userInfoService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(UserInfo userInfo) {
        userInfoService.save(userInfo);
        return userInfo.getId();
    }

    @PostMapping("/edit")
    public boolean edit(UserInfo userInfo) {
        return userInfoService.updateById(userInfo);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return userInfoService.removeById(id);
    }

}
