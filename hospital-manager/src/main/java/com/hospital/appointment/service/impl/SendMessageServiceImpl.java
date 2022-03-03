package com.hospital.appointment.service.impl;

import com.hospital.appointment.entity.ChatInfo;
import com.hospital.appointment.service.ChatInfoService;
import com.hospital.appointment.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendMessageServiceImpl implements SendMessageService {
    @Autowired
    private ChatInfoService chatInfoService;

    @Override
    public boolean saveChatInfo(ChatInfo chatInfo) {
        boolean save = chatInfoService.save(chatInfo);
        return save;
    }
}
