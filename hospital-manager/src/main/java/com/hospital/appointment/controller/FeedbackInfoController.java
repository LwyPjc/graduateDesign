package com.hospital.appointment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hospital.appointment.entity.FeedbackInfo;
import com.hospital.appointment.service.FeedbackInfoService;

import java.io.Serializable;
import java.util.List;

import static com.hospital.appointment.utils.CommUtils.getTimestamp;

/**
 * <p>标题: 服务 - 请求控制层</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: mic
 * @date 2022-2-13
 */
@RestController
@RequestMapping("/feedbackInfo")
@CrossOrigin
public class FeedbackInfoController {

    @Autowired
    private FeedbackInfoService feedbackInfoService;

    @GetMapping("/findList")
    public List<FeedbackInfo> findList(FeedbackInfo feedbackInfo) {
        return feedbackInfoService.findList(feedbackInfo);
    }

    @GetMapping("/findListByPage")
    public Page<FeedbackInfo> findListByPage(FeedbackInfo feedbackInfo, Page page) {
        QueryWrapper<FeedbackInfo> queryWrapper = new QueryWrapper<>(feedbackInfo);
        return feedbackInfoService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public FeedbackInfo getById(@PathVariable String id) {
        return feedbackInfoService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(@RequestBody FeedbackInfo feedbackInfo) {
        feedbackInfo.setCreateTime(getTimestamp());
        feedbackInfoService.save(feedbackInfo);
        return feedbackInfo.getId();
    }

    @PostMapping("/edit")
    public boolean edit(FeedbackInfo feedbackInfo) {
        return feedbackInfoService.updateById(feedbackInfo);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return feedbackInfoService.removeById(id);
    }

}
