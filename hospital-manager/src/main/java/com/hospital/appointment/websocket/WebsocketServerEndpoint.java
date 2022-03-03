package com.hospital.appointment.websocket;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import com.hospital.appointment.entity.ChatInfo;
import com.hospital.appointment.service.ChatInfoService;
import com.hospital.appointment.service.SendMessageService;
import com.hospital.appointment.websocket.handler.ChatWsHandler;
import com.hospital.appointment.websocket.handler.KfWsHandler;
import com.hospital.appointment.websocket.handler.WsHandler;
import com.hospital.appointment.websocket.store.WsReqPayLoad;
import com.hospital.appointment.websocket.store.WsRespPayLoad;
import com.hospital.appointment.websocket.store.WsStore;
import com.hospital.appointment.websocket.store.WsUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/ws")

public class WebsocketServerEndpoint {

    private static final Logger log = LoggerFactory.getLogger(WebsocketServerEndpoint.class);

    private static SendMessageService sendMessageSercice;
    private Session session;

    private static CopyOnWriteArraySet<WebsocketServerEndpoint> wsSet = new CopyOnWriteArraySet<>();

    @Autowired
    public void setSendMessageSercice(SendMessageService sendMessageSercice) {
        this.sendMessageSercice = sendMessageSercice;
    }

//    private static Map<String, WsHandler> wsHandler = Maps.newConcurrentMap();
//
//    static {
//        wsHandler.put("robot", new KfWsHandler());
//        wsHandler.put("chat", new ChatWsHandler());
//    }

    @OnOpen
    public void onOpen(Session session) {
        log.info("New ws connection {} ", session.getId());
        this.session = session;
        wsSet.add(this);
        WsStore.put(session.getId(), WsUser.WsUserBuilder.aWsUser().id(session.getId()).session(session).build());
        respMsg(WsRespPayLoad.ok().toJson());
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
//        WsStore.remove(session.getId());
        wsSet.remove(this);
        log.warn("ws closed，reason:{}", closeReason);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("onMEssage-session--" + session.getId());
        log.info("accept client messages: {}" + message);
//        WsReqPayLoad payLoad = JSON.parseObject(message, WsReqPayLoad.class);
        ChatInfo chatInfo = JSON.parseObject(message, ChatInfo.class);
        if (StringUtils.isBlank(chatInfo.getSendFrom())) {
            respMsg(WsRespPayLoad.ofError("Type is null.").toJson());
            return;
        }
        //save to db
        sendMessageSercice.saveChatInfo(chatInfo);

//        WsUser wsUser = WsStore.get(session.getId());
//        if (null == wsUser || StringUtils.isBlank(wsUser.getUsername())) {
//            String sendFrom = chatInfo.getSendFrom();
//            WsStore.put(session.getId(), WsUser.WsUserBuilder.aWsUser()
//                    .id(session.getId())
//                    .userId("0".equals(sendFrom) ? chatInfo.getOpenid() : String.valueOf(chatInfo.getDocId()))
//                    .username("0".equals(sendFrom) ? chatInfo.getTrueName() : chatInfo.getDocName())
//                    .build());
//        }
        for (WebsocketServerEndpoint ws : wsSet) {
            ws.respMsg(chatInfo.toJson());
        }
//        WsHandler handler = wsHandler.get(payLoad.getType());
//        if (null != handler) {
//            WsRespPayLoad resp = handler.onMessage(session, payLoad);
//            if (null != resp) {
//                respMsg(session, resp.toJson());
//            }
//        } else {
//            respMsg(session, WsRespPayLoad.ok().toJson());
//        }
    }

    @OnError
    public void onError(Session session, Throwable e) {
        WsStore.remove(session.getId());
        log.error("WS Error: ", e);
    }

    private void respMsg(String content) {
        try {
            this.session.getBasicRemote().sendText(content);
//            this.session.getAsyncRemote().sendText(content);


        } catch (IOException e) {
            log.error("Ws resp msg error {} {}", content, e);
        }
    }
}
