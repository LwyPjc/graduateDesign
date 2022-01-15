package com.graduation.warning.entity.dto;

import com.graduation.warning.entity.OpenCourse;

/**
 * 学生信息前端交互类
 */
public class OpenCourseDto extends OpenCourse {
    /** 学院 */
    private String college;

    /** 年级 */
    private String grade;

    /** 专业 */
    private String subject;

    /** 班级 */
    private String classNo;
    /** 课程名称 */
    private String courseName;
    /** 教师名称 */
    private String teacherName;
    /** 教室名称 */
    private String roomName;


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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
