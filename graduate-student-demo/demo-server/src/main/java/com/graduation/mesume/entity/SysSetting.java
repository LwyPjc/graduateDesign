package com.graduation.mesume.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 系统配置 
 * </p>
 *
 * @author auth
 * @since 2021-02-08
 */
public class SysSetting extends Model<SysSetting> {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
      @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 编号 一组配置的编号
     */
    private String code;

    /**
     * 内容
     */
    private String content;

    /**
     * 顺序 在组内的顺序（非必须）
     */
    @TableField("orderNum")
    private Integer orderNum;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysSetting{" +
        "id=" + id +
        ", code=" + code +
        ", content=" + content +
        ", orderNum=" + orderNum +
        ", createdTime=" + createdTime +
        "}";
    }
}
