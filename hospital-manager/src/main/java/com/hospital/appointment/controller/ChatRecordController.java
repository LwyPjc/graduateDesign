package com.hospital.appointment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital.appointment.entity.ChatRecord;
import com.hospital.appointment.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.Serializable;
import java.util.List;

/**
 * <p>标题: 聊天记录 - 请求控制层</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: mic
 * @date 2022-3-3
 */
@CrossOrigin
@RestController
@RequestMapping("/chatRecord")
public class ChatRecordController {

    @Autowired
    private ChatRecordService chatRecordService;

    @GetMapping("/findList")
    public List<ChatRecord> findList(ChatRecord chatRecord) {
        return chatRecordService.findList(chatRecord);
    }

    @GetMapping("/findListByPage")
    public Page<ChatRecord> findListByPage(ChatRecord chatRecord, Page page) {
        QueryWrapper<ChatRecord> queryWrapper = new QueryWrapper<>(chatRecord);
        return chatRecordService.page(page, queryWrapper);
    }

}
