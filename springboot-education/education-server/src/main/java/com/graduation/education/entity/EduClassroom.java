package com.graduation.education.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 教室信息
 * </p>
 *
 * @author auth
 * @since 2021-03-29
 */
public class EduClassroom extends Model<EduClassroom> {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
      @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 教室名
     */
    private String name;


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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "EduClassroom{" +
        "id=" + id +
        ", name=" + name +
        "}";
    }
}
