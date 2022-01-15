package com.graduation.warning.entity.dto;

import com.graduation.warning.entity.Class;

/**
 * 班级信息前端交互类
 */
public class ClassDto extends Class {
    /** 辅导员姓名 */
    private String counselorName;

    public String getCounselorName() {
        return counselorName;
    }

    public void setCounselorName(String counselorName) {
        this.counselorName = counselorName;
    }
}
