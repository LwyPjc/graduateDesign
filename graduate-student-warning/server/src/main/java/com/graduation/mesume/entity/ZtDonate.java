package com.graduation.mesume.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 捐赠信息
 * </p>
 *
 * @author auth
 * @since 2021-02-01
 */
public class ZtDonate extends Model<ZtDonate> {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
      @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 物品ID
     */
    private Integer goodsId;

    /**
     * 捐赠时间
     */
    private LocalDateTime donateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public LocalDateTime getDonateTime() {
        return donateTime;
    }

    public void setDonateTime(LocalDateTime donateTime) {
        this.donateTime = donateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ZtDonate{" +
        "id=" + id +
        ", userId=" + userId +
        ", goodsId=" + goodsId +
        ", donateTime=" + donateTime +
        "}";
    }
}
