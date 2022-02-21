package com.hospital.appointment.websocket.handler;


import com.hospital.appointment.websocket.store.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.util.Date;
import java.util.List;

public class ChatWsHandler implements WsHandler {

    private static final Logger log = LoggerFactory.getLogger(ChatWsHandler.class);

    @Override
    public WsRespPayLoad onMessage(Session session, WsReqPayLoad payLoad) {
        // 广播消息
        List<WsUser> allSessions = WsStore.getAll();
        for (WsUser s : allSessions) {
            WsRespPayLoad resp = WsRespPayLoad.WsRespPayLoadBuilder.aWsRespPayLoad()
              .data(
                WsChatResp.WsChatRespBuilder.aWsChatResp()
                  .username(payLoad.getUsername())
                  .avatar(payLoad.getAvatar())
                  .msg(payLoad.getData())
                  .createdTime(new Date())
                  .self(s.getId().equals(session.getId()))
                  .build()
              )
              .build();
            log.info("Broadcast message {} {} ", s.getId(), s.getUsername());
            s.getSession().getAsyncRemote().sendText(resp.toJson());
        }
        return null;
    }

}
