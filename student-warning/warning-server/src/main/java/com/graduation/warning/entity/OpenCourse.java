package com.graduation.warning.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 开课信息
 * </p>
 *
 * @author auth
 * @since 2022-02-19
 */
public class OpenCourse extends Model<OpenCourse> {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
      @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 学期
     */
    private String term;

    /**
     * 开课学院
     */
    private String college;

    /**
     * 班级id
     */
    private Integer classId;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 学分
     */
    private BigDecimal score;

    /**
     * 学时
     */
    private Integer hour;

    /**
     * 人数
     */
    private Integer number;

    /**
     * 开始周
     */
    private Integer weekStart;

    /**
     * 结束周
     */
    private Integer weekEnd;

    /** 周次;一周多节的按逗号隔开，如 1,3表示周一和周三 */
    private String weekTime ;

    /**
     * 开始节
     */
    private Integer nodeStart;

    /**
     * 结束节
     */
    private Integer nodeEnd;

    /**
     * 教师id
     */
    private Integer teacherId;

    /**
     * 教室id
     */
    private Integer classroomId;
    /** 选课开始时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date chooseStart ;
    /** 选课结束时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date chooseEnd ;
    /** 当前选课人数 */
    private Integer chooseNum ;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(Integer weekStart) {
        this.weekStart = weekStart;
    }

    public Integer getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(Integer weekEnd) {
        this.weekEnd = weekEnd;
    }

    public Integer getNodeStart() {
        return nodeStart;
    }

    public void setNodeStart(Integer nodeStart) {
        this.nodeStart = nodeStart;
    }

    public Integer getNodeEnd() {
        return nodeEnd;
    }

    public void setNodeEnd(Integer nodeEnd) {
        this.nodeEnd = nodeEnd;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    public Date getChooseStart() {
        return chooseStart;
    }

    public void setChooseStart(Date chooseStart) {
        this.chooseStart = chooseStart;
    }

    public Date getChooseEnd() {
        return chooseEnd;
    }

    public void setChooseEnd(Date chooseEnd) {
        this.chooseEnd = chooseEnd;
    }

    public Integer getChooseNum() {
        return chooseNum;
    }

    public void setChooseNum(Integer chooseNum) {
        this.chooseNum = chooseNum;
    }

    public String getWeekTime() {
        return weekTime;
    }

    public void setWeekTime(String weekTime) {
        this.weekTime = weekTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OpenCourse{" +
        "id=" + id +
        ", term=" + term +
        ", college=" + college +
        ", classId=" + classId +
        ", courseId=" + courseId +
        ", score=" + score +
        ", hour=" + hour +
        ", number=" + number +
        ", weekStart=" + weekStart +
        ", weekEnd=" + weekEnd +
        ", nodeStart=" + nodeStart +
        ", nodeEnd=" + nodeEnd +
        ", teacherId=" + teacherId +
        ", classroomId=" + classroomId +
        "}";
    }
}
