package com.hospital.appointment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hospital.appointment.dao.ChatRecordMapper;
import com.hospital.appointment.entity.ChatInfo;
import com.hospital.appointment.entity.ChatRecord;
import com.hospital.appointment.service.ChatRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;


/**
 * <p>标题: 服务 - 业务逻辑层</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: mic
 * @date 2022-3-3
 */
@Service
public class ChatRecordServiceImpl extends ServiceImpl<ChatRecordMapper, ChatRecord> implements ChatRecordService {

    private static final Logger log = LoggerFactory.getLogger(ChatRecordServiceImpl.class);

    /**
     * 保存关联关系
     *
     * @param chatInfo
     */
    @Override
    @Async
    public void saveChatAssociation(ChatInfo chatInfo) {
        QueryWrapper<ChatRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", chatInfo.getOpenid());
        queryWrapper.eq("doc_id", chatInfo.getDocId());

        ChatRecord one = this.getOne(queryWrapper);
        if (one == null) {
            one = new ChatRecord();
            one.setDocId(chatInfo.getDocId());
            one.setOpenid(chatInfo.getOpenid());
            one.setDocName(chatInfo.getDocName());
            one.setTrueName(chatInfo.getTrueName());
            this.save(one);
            log.info("保存关联关系");
        }
    }
}
