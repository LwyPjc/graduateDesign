package com.graduation.mesume.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author auth
 * @since 2021-01-27
 */
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phone;

    /**
     * 名称
     */
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        return "SysUser{" +
        ", id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", phone=" + phone +
        "}";
    }
}
