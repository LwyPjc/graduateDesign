package com.graduation.warning.entity.dto;

import com.graduation.warning.entity.Student;

/**
 * 学生信息前端交互类
 */
public class StudentDto extends Student {
    /** 学院 */
    private String college;

    /** 年级 */
    private String grade;

    /** 专业 */
    private String subject;

    /** 班级 */
    private String classNo;
    /** 辅导员姓名 */
    private String counselorName;

    /** 性别中文 */
    private String sexChn;

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getCounselorName() {
        return counselorName;
    }

    public void setCounselorName(String counselorName) {
        this.counselorName = counselorName;
    }

    public String getSexChn() {
        if(getSex() == null){
            return "";
        }
        return 0 == getSex()? "女":"男";
    }

    public void setSexChn(String sexChn) {
        this.sexChn = sexChn;
    }
}
