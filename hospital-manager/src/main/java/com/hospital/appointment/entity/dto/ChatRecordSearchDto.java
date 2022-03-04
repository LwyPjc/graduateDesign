package com.hospital.appointment.entity.dto;

import java.io.Serializable;

/**
 * 搜索对象
 */
public class ChatRecordSearchDto implements Serializable {
    private String trueName;

    private String docId;

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }
}
