package com.hiteamtech.uws.resultmapping.tpl;
/**
 * Created by William shaw on 2017/9/22.
 */

import java.sql.Timestamp;
import java.util.Date;

/**
 * 作者 Wailliam shaw
 * 功能：Banner实体类
 * 属性：图片，跳转链接
 * */
public class BannerNode {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBannerId() {
        return bannerId;
    }

    public void setBannerId(int bannerId) {
        this.bannerId = bannerId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public String getUndercarriageTime() {
        return undercarriageTime;
    }

    public void setUndercarriageTime(String undercarriageTime) {
        this.undercarriageTime = undercarriageTime;
    }

    public String getGroundingTime() {
        return groundingTime;
    }

    public void setGroundingTime(String groundingTime) {
        this.groundingTime = groundingTime;
    }

    /**
     * 映射图片链接
     * */
    private int id;
    private String imgUrl;
    private String jumpUrl;
    private String title;
    private int bannerId;
    private int status;
    private Date createTime;
    private int sortNum;
    private String undercarriageTime;
    private String groundingTime;

    @Override
    public String toString() {
        return "BannerNode{" +
                "id=" + id +
                ", imgUrl='" + imgUrl + '\'' +
                ", jumpUrl='" + jumpUrl + '\'' +
                ", title='" + title + '\'' +
                ", bannerId=" + bannerId +
                ", status=" + status +
                ", createTime=" + createTime +
                ", sortNum=" + sortNum +
                ", undercarriageTime=" + undercarriageTime +
                ", groundingTime=" + groundingTime +
                '}';
    }
}
