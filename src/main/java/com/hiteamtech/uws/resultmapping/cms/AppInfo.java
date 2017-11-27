package com.hiteamtech.uws.resultmapping.cms;

/**
 * Created by BigK on 2017/9/21.
 */

import java.util.Date;

/**
 * Updated by Wailliam Shaw on 2017/9/29.
 */

public class AppInfo {
    private int id;//产品ID
    private String appName;//APP名字
    private int userId;//用户ID
    private Date createTime;//创建时间
    private String appIntroduction;//产品描述
    private boolean status;//状态

    @Override
    public String toString() {
        return "AppInfo{" +
                "id=" + id +
                ", appName='" + appName + '\'' +
                ", userId=" + userId +
                ", createTime='" + createTime + '\'' +
                ", appIntroduction='" + appIntroduction + '\'' +
                ", status=" + status +
                '}';
    }

    public boolean isStatus() {
        return status;
    }


    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppIntroduction() {
        return appIntroduction;
    }

    public void setAppIntroduction(String appIntroduction) {
        this.appIntroduction = appIntroduction;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
