package com.hospital.appointment.service.impl;

import com.hospital.appointment.entity.ChatInfo;
import com.hospital.appointment.service.ChatInfoService;
import com.hospital.appointment.service.ChatRecordService;
import com.hospital.appointment.service.SendMessageService;
import com.hospital.appointment.service.StatisticsHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendMessageServiceImpl implements SendMessageService {
    @Autowired
    private ChatInfoService chatInfoService;

    @Autowired
    private StatisticsHandlerService statisticsHandlerService;

    @Autowired
    private ChatRecordService chatRecordService;

    @Override
    public boolean saveChatInfo(ChatInfo chatInfo) {
        boolean save = chatInfoService.save(chatInfo);
        // 异步保存统计医生咨询数
        statisticsHandlerService.saveOrUpdate(chatInfo.getDocName(), chatInfo.getDocId(), chatInfo.getOpenid());

        // 医生 患者聊天关联表
        chatRecordService.saveChatAssociation(chatInfo);
        return save;
    }
}
