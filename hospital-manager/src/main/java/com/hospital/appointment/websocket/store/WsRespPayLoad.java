package com.hospital.appointment.websocket.store;

import com.alibaba.fastjson.JSONObject;

public class WsRespPayLoad {
    protected String username;
    protected String type;
    protected int code;
    private Object data;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static WsRespPayLoad of(int code, Object data) {
        return WsRespPayLoadBuilder.aWsRespPayLoad()
                .code(code)
                .data(data)
                .build();
    }

    public static WsRespPayLoad ofError(String message) {
        return WsRespPayLoadBuilder.aWsRespPayLoad()
                .code(400)
                .data(message)
                .build();
    }

    public static WsRespPayLoad of(Object data) {
        return WsRespPayLoadBuilder.aWsRespPayLoad().data(data).build();
    }

    public static WsRespPayLoad ok() {
        return of("ok");
    }

    public String toJson() {
        return JSONObject.toJSONString(this);
    }


    public static final class WsRespPayLoadBuilder {
        protected String username;
        protected String type;
        protected int code;
        private Object data;

        private WsRespPayLoadBuilder() {
        }

        public static WsRespPayLoadBuilder aWsRespPayLoad() {
            return new WsRespPayLoadBuilder();
        }

        public WsRespPayLoadBuilder username(String username) {
            this.username = username;
            return this;
        }

        public WsRespPayLoadBuilder type(String type) {
            this.type = type;
            return this;
        }

        public WsRespPayLoadBuilder code(int code) {
            this.code = code;
            return this;
        }

        public WsRespPayLoadBuilder data(Object data) {
            this.data = data;
            return this;
        }

        public WsRespPayLoad build() {
            WsRespPayLoad wsRespPayLoad = new WsRespPayLoad();
            wsRespPayLoad.setUsername(username);
            wsRespPayLoad.setType(type);
            wsRespPayLoad.setCode(code);
            wsRespPayLoad.setData(data);
            return wsRespPayLoad;
        }
    }
}
