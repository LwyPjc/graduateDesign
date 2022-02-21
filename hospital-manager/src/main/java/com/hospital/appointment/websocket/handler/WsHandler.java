package com.hospital.appointment.websocket.handler;


import com.hospital.appointment.websocket.store.WsReqPayLoad;
import com.hospital.appointment.websocket.store.WsRespPayLoad;

import javax.websocket.Session;

public interface WsHandler {

    WsRespPayLoad onMessage(Session session, WsReqPayLoad payLoad);

}
