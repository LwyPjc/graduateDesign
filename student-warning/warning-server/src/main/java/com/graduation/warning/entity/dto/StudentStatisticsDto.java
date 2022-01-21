package com.graduation.warning.entity.dto;

import com.graduation.warning.entity.StudentStatistics;

public class StudentStatisticsDto extends StudentStatistics {
    /**
     * 家庭联系方式
     */
    private String familyPhone;

    public String getFamilyPhone() {
        return familyPhone;
    }

    public void setFamilyPhone(String familyPhone) {
        this.familyPhone = familyPhone;
    }
}
