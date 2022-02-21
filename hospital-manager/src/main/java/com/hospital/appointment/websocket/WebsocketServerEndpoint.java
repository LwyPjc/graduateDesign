package com.hospital.appointment.websocket;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

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
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;

@Component
@ServerEndpoint("/ws")
public class WebsocketServerEndpoint {

    private static final Logger log = LoggerFactory.getLogger(WebsocketServerEndpoint.class);

    private static Map<String, WsHandler> wsHandler = Maps.newConcurrentMap();

    static {
        wsHandler.put("robot", new KfWsHandler());
        wsHandler.put("chat", new ChatWsHandler());
    }

    @OnOpen
    public void onOpen(Session session) {
        log.info("New ws connection {} ", session.getId());
        WsStore.put(session.getId(), WsUser.WsUserBuilder.aWsUser().id(session.getId()).session(session).build());
        respMsg(session, WsRespPayLoad.ok().toJson());
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        WsStore.remove(session.getId());
        log.warn("ws closed，reason:{}", closeReason);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("accept client messages: {}" + message);
        WsReqPayLoad payLoad = JSON.parseObject(message, WsReqPayLoad.class);
        if (StringUtils.isBlank(payLoad.getType())) {
            respMsg(session, WsRespPayLoad.ofError("Type is null.").toJson());
            return;
        }
        WsUser wsUser = WsStore.get(session.getId());
        if (null == wsUser || StringUtils.isBlank(wsUser.getUsername())) {
            WsStore.put(session.getId(), WsUser.WsUserBuilder.aWsUser()
                    .id(session.getId())
                    .username(payLoad.getUsername())
                    .avatar(payLoad.getAvatar())
                    .session(session)
                    .build()
            );
        }
        WsHandler handler = wsHandler.get(payLoad.getType());
        if (null != handler) {
            WsRespPayLoad resp = handler.onMessage(session, payLoad);
            if (null != resp) {
                respMsg(session, resp.toJson());
            }
        } else {
            respMsg(session, WsRespPayLoad.ok().toJson());
        }
    }

    @OnError
    public void onError(Session session, Throwable e) {
        WsStore.remove(session.getId());
        log.error("WS Error: ", e);
    }

    private void respMsg(Session session, String content) {
        try {
            session.getBasicRemote().sendText(content);
        } catch (IOException e) {
            log.error("Ws resp msg error {} {}", content, e);
        }
    }
}
