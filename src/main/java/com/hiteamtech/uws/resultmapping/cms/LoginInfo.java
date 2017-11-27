package com.hiteamtech.uws.resultmapping.cms;

/**
 * Created by BigK on 2017/10/26.
 */
public class LoginInfo {

    private int userId;
    private String userName;
    private String organizationName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
