package com.hospital.appointment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital.appointment.entity.UserInfo;
import com.hospital.appointment.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.hospital.appointment.entity.FeedbackInfo;
import com.hospital.appointment.service.FeedbackInfoService;

import java.io.Serializable;
import java.util.HashMap;
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

    @Autowired
    private UserInfoService userInfoService;


    @GetMapping("/findList")
    public List<FeedbackInfo> findList(FeedbackInfo feedbackInfo) {
        return feedbackInfoService.findList(feedbackInfo);
    }

    @GetMapping("/findListByPage")
    public Page<FeedbackInfo> findListByPage(FeedbackInfo feedbackInfo, Page page) {
        QueryWrapper<FeedbackInfo> queryWrapper = new QueryWrapper<>(feedbackInfo);
        Page<FeedbackInfo> pageInfo = feedbackInfoService.page(page, queryWrapper);
        HashMap<String, String> nameMap = new HashMap<>();
        if (pageInfo != null) {
            List<FeedbackInfo> orders = pageInfo.getRecords();
            if (!CollectionUtils.isEmpty(orders)) {
                for (FeedbackInfo order : orders) {
                    String openid = order.getOpenid();
                    if (nameMap.containsKey(openid)) {
                        order.setOpenid(nameMap.get(openid));
                        continue;
                    }
                    UserInfo userInfo = new UserInfo();
                    userInfo.setId(openid);
                    QueryWrapper<UserInfo> queryWrapperFeed = new QueryWrapper<>(userInfo);
                    queryWrapperFeed.eq("id", userInfo.getId());
                    List<UserInfo> list = userInfoService.list(queryWrapperFeed);
                    if (!CollectionUtils.isEmpty(list)) {
                        UserInfo userInfo1 = list.get(0);
                        String trueName = userInfo1.getTrueName();
                        String nickName = userInfo1.getNickName();
                        if (StringUtils.isNotBlank(trueName)) {
                            order.setOpenid(trueName);
                        } else {
                            order.setOpenid(nickName);
                        }
                        nameMap.put(openid, order.getOpenid());
                    }
                }
            }
        }
        return pageInfo;
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
