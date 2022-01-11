package com.graduation.education.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 课表信息
 * </p>
 *
 * @author auth
 * @since 2021-03-29
 */
public class EduCourse extends Model<EduCourse> {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
      @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 开课学院
     */
    private String college;

    /**
     * 课程编码
     */
    private String code;

    /**
     * 中文名称
     */
    private String name;

    /**
     * 英文名称
     */
    private String nameEng;

    /**
     * 课程类别
     */
    private String type;


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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "EduCourse{" +
        "id=" + id +
        ", college=" + college +
        ", code=" + code +
        ", name=" + name +
        ", nameEng=" + nameEng +
        ", type=" + type +
        "}";
    }
}
