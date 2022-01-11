package com.graduation.mesume.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 物品信息
 * </p>
 *
 * @author auth
 * @since 2021-02-01
 */
public class ZtGoods extends Model<ZtGoods> {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
      @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 朝代
     */
    private String dynasty;

    /**
     * 名称
     */
    private String name;

    /**
     * 图片地址
     */
    @TableField("photoUrl")
    private String photoUrl;

    /**
     * 简介
     */
    private String produce;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 种类
     */
    private Integer type;

    /**
     * 图片名称
     */
    @TableField("photoName")
    private String photoName;

    /**
     * 状态(0 审核中   1 审核通过)
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getProduce() {
        return produce;
    }

    public void setProduce(String produce) {
        this.produce = produce;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ZtGoods{" +
        "id=" + id +
        ", dynasty=" + dynasty +
        ", name=" + name +
        ", photoUrl=" + photoUrl +
        ", produce=" + produce +
        ", price=" + price +
        ", type=" + type +
        "}";
    }
}
