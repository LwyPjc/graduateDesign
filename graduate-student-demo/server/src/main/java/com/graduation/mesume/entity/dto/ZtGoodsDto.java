package com.graduation.mesume.entity.dto;

import com.graduation.mesume.entity.ZtGoods;

import java.time.LocalDateTime;

/**
 * 扩展ZtGoods的实体类
 */
public class ZtGoodsDto extends ZtGoods {
    /**
     * 捐款总额
     */
    private Double donatePrice;

    /**
     * 收藏总数
     */
    private Integer collectCnt;

    /**
     * 类别-中文
    */
    private String typeChn;

    /**
     * 是否被收藏
     */
    private boolean collect;

    /**
     * 用户名称  sysUser name（捐赠者）
     */
    private String userName;

    /**
     * 捐赠时间
     */
    private LocalDateTime donateTime;
    /**
     *  状态-中文
     */
    private String statusChn;

    /**
     * 用户id（ztDonate user_id）
     */
    private String userId;

    public Double getDonatePrice() {
        return donatePrice;
    }

    public void setDonatePrice(Double donatePrice) {
        this.donatePrice = donatePrice;
    }

    public Integer getCollectCnt() {
        return collectCnt;
    }

    public void setCollectCnt(Integer collectCnt) {
        this.collectCnt = collectCnt;
    }

    public String getTypeChn() {
        return typeChn;
    }

    public void setTypeChn(String typeChn) {
        this.typeChn = typeChn;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getDonateTime() {
        return donateTime;
    }

    public void setDonateTime(LocalDateTime donateTime) {
        this.donateTime = donateTime;
    }

    public String getStatusChn() {
        return statusChn;
    }

    public void setStatusChn(String statusChn) {
        this.statusChn = statusChn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
