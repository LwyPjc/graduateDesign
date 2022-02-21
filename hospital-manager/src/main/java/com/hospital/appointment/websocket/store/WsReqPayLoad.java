package com.hospital.appointment.websocket.store;

public class WsReqPayLoad {
    protected String username;
    protected String avatar;
    protected String type;
    private String data;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static final class WsReqPayLoadBuilder {
        protected String username;
        protected String avatar;
        protected String type;
        private String data;

        private WsReqPayLoadBuilder() {
        }

        public static WsReqPayLoadBuilder aWsReqPayLoad() {
            return new WsReqPayLoadBuilder();
        }

        public WsReqPayLoadBuilder username(String username) {
            this.username = username;
            return this;
        }

        public WsReqPayLoadBuilder avatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public WsReqPayLoadBuilder type(String type) {
            this.type = type;
            return this;
        }

        public WsReqPayLoadBuilder data(String data) {
            this.data = data;
            return this;
        }

        public WsReqPayLoad build() {
            WsReqPayLoad wsReqPayLoad = new WsReqPayLoad();
            wsReqPayLoad.setUsername(username);
            wsReqPayLoad.setAvatar(avatar);
            wsReqPayLoad.setType(type);
            wsReqPayLoad.setData(data);
            return wsReqPayLoad;
        }
    }
}
