package com.graduation.warning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 选课信息
 * </p>
 *
 * @author auth
 * @since 2022-02-19
 */
public class StudentCourse extends Model<StudentCourse> {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
      @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 学生id
     */
    private Integer studentId;

    /**
     * 开课课程id
     */
    private Integer openCourseId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getOpenCourseId() {
        return openCourseId;
    }

    public void setOpenCourseId(Integer openCourseId) {
        this.openCourseId = openCourseId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
        "id=" + id +
        ", studentId=" + studentId +
        ", openCourseId=" + openCourseId +
        "}";
    }
}
