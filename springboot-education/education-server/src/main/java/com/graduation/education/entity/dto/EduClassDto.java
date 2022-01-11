package com.graduation.education.entity.dto;

import com.graduation.education.entity.EduClass;

/**
 * 班级信息前端交互类
 */
public class EduClassDto extends EduClass {
    /** 辅导员姓名 */
    private String counselorName;

    public String getCounselorName() {
        return counselorName;
    }

    public void setCounselorName(String counselorName) {
        this.counselorName = counselorName;
    }
}
