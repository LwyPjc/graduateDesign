package com.graduation.education.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 教师信息
 * </p>
 *
 * @author auth
 * @since 2021-03-29
 */
public class EduTeacher extends Model<EduTeacher> {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
      @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 工号
     */
    private String code;

    /**
     * 关联班级id
     */
    private String relateClassIds;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRelateClassIds() {
        return relateClassIds;
    }

    public void setRelateClassIds(String relateClassIds) {
        this.relateClassIds = relateClassIds;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "EduTeacher{" +
        "id=" + id +
        ", name=" + name +
        "}";
    }
}
