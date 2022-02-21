package com.hospital.appointment.websocket.store;

import com.alibaba.fastjson.JSON;

import java.util.Date;


public class WsChatResp {
    private String username;
    private String avatar;
    private String msg;
    private boolean self;
    private Date createdTime;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSelf() {
        return self;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }


    public static final class WsChatRespBuilder {
        private String username;
        private String avatar;
        private String msg;
        private boolean self;
        private Date createdTime;

        private WsChatRespBuilder() {
        }

        public static WsChatRespBuilder aWsChatResp() {
            return new WsChatRespBuilder();
        }

        public WsChatRespBuilder username(String username) {
            this.username = username;
            return this;
        }

        public WsChatRespBuilder avatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public WsChatRespBuilder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public WsChatRespBuilder self(boolean self) {
            this.self = self;
            return this;
        }

        public WsChatRespBuilder createdTime(Date createdTime) {
            this.createdTime = createdTime;
            return this;
        }

        public WsChatResp build() {
            WsChatResp wsChatResp = new WsChatResp();
            wsChatResp.setUsername(username);
            wsChatResp.setAvatar(avatar);
            wsChatResp.setMsg(msg);
            wsChatResp.setSelf(self);
            wsChatResp.setCreatedTime(createdTime);
            return wsChatResp;
        }
    }
}
