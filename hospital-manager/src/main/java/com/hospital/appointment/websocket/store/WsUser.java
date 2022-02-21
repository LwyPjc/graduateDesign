package com.hospital.appointment.websocket.store;

import javax.websocket.Session;

public class WsUser {

    // sessionId
    private String id;

    private Session session;
    private String username;
    private String avatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

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


    public static final class WsUserBuilder {
        // sessionId
        private String id;
        private Session session;
        private String username;
        private String avatar;

        private WsUserBuilder() {
        }

        public static WsUserBuilder aWsUser() {
            return new WsUserBuilder();
        }

        public WsUserBuilder id(String id) {
            this.id = id;
            return this;
        }

        public WsUserBuilder session(Session session) {
            this.session = session;
            return this;
        }

        public WsUserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public WsUserBuilder avatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public WsUser build() {
            WsUser wsUser = new WsUser();
            wsUser.setId(id);
            wsUser.setSession(session);
            wsUser.setUsername(username);
            wsUser.setAvatar(avatar);
            return wsUser;
        }
    }
}
