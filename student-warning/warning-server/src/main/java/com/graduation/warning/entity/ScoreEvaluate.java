package com.graduation.warning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;

/**
 * <p>标题: score_evaluate实体</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: auth
 * @date 2022-1-17
 */
public class ScoreEvaluate extends Model<ScoreEvaluate> {

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer ID;

    private Integer studentId;

    private Integer teacherId;

    private Integer openCourseId;

    private java.math.BigDecimal score;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getOpenCourseId() {
        return openCourseId;
    }

    public void setOpenCourseId(Integer openCourseId) {
        this.openCourseId = openCourseId;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}
