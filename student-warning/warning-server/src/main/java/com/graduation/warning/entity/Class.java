package com.graduation.warning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 班级信息
 * </p>
 *
 * @author auth
 * @since 2022-02-19
 */
public class Class extends Model<Class> {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
      @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 学院
     */
    private String college;

    /**
     * 年级
     */
    private String grade;

    /**
     * 专业
     */
    private String subject;

    /**
     * 班级
     */
    private String classNo;

    /**
     * 专业方向
     */
    private String subjectDir;

    /**
     * 辅导员id
     */
    private Integer counselorId;




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getSubjectDir() {
        return subjectDir;
    }

    public void setSubjectDir(String subjectDir) {
        this.subjectDir = subjectDir;
    }

    public Integer getCounselorId() {
        return counselorId;
    }

    public void setCounselorId(Integer counselorId) {
        this.counselorId = counselorId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Class{" +
        "id=" + id +
        ", college=" + college +
        ", grade=" + grade +
        ", subject=" + subject +
        ", classNo=" + classNo +
        ", subjectDir=" + subjectDir +
        "}";
    }
}
