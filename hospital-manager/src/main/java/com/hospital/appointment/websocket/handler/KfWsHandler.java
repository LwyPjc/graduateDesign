package com.hospital.appointment.websocket.handler;

import com.hospital.appointment.websocket.store.WsReqPayLoad;
import com.hospital.appointment.websocket.store.WsRespPayLoad;

import javax.websocket.Session;

public class KfWsHandler implements WsHandler {

    @Override
    public WsRespPayLoad onMessage(Session session, WsReqPayLoad payLoad) {
        return WsRespPayLoad.of("您好");
    }

}
