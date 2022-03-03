package com.hospital.appointment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital.appointment.entity.dto.ChatInfoDo;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hospital.appointment.entity.ChatInfo;
import com.hospital.appointment.service.ChatInfoService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.hospital.appointment.utils.CommUtils.date2String;

/**
 * <p>标题: 服务 - 请求控制层</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: mic
 * @date 2022-3-2
 */
@RestController
@RequestMapping("/chatInfo")
public class ChatInfoController {

    @Autowired
    private ChatInfoService chatInfoService;

    @GetMapping("/findList")
    public List<ChatInfo> findList(ChatInfo chatInfo) {
        return chatInfoService.findList(chatInfo);
    }

    @GetMapping("/findListByPage")
    public Page<ChatInfo> findListByPage(ChatInfo chatInfo, Page page) {
        QueryWrapper<ChatInfo> queryWrapper = new QueryWrapper<>(chatInfo);
        return chatInfoService.page(page, queryWrapper);
    }

    @GetMapping("/findByDoubleIds")
    public List<ChatInfoDo> findByOpenidAndDocId(@RequestParam String openid, @RequestParam String docId) {
        QueryWrapper<ChatInfo> queryWrapper = new QueryWrapper<>();
        Integer docId1 = Integer.parseInt(docId);
        queryWrapper.eq("openid", openid);
        queryWrapper.eq("doc_id", docId1);
        queryWrapper.orderByAsc("create_time");
        List<ChatInfo> listChatInfo = chatInfoService.list(queryWrapper);
        List<ChatInfoDo> list = listChatInfo.stream().map(info -> {
            ChatInfoDo chatInfoDo = new ChatInfoDo();
            chatInfoDo.setContent(info.getContent());
            chatInfoDo.setOpenid(info.getOpenid());
            chatInfoDo.setDocId(info.getDocId());
            chatInfoDo.setSendFrom(info.getSendFrom());
            chatInfoDo.setTrueName(info.getTrueName());
            Date createTime = info.getCreateTime();
            // todo 时间转换
//            chatInfoDo.setCreateTime();
            return chatInfoDo;
        }).collect(Collectors.toList());
        return list;
    }

    @GetMapping("/{id}")
    public ChatInfo getById(@PathVariable String id) {
        return chatInfoService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(ChatInfo chatInfo) {
        chatInfoService.save(chatInfo);
        return chatInfo.getId();
    }

    @PostMapping("/edit")
    public boolean edit(ChatInfo chatInfo) {
        return chatInfoService.updateById(chatInfo);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return chatInfoService.removeById(id);
    }

}
