package com.hospital.appointment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital.appointment.entity.UserInfo;
import com.hospital.appointment.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * <p>标题: 服务 - 请求控制层</p>
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
    public UserInfo getById(@PathVariable("id") String id) {
        return userInfoService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(@RequestBody UserInfo userInfo) {
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
