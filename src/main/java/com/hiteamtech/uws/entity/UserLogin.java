package com.hiteamtech.uws.entity;

/**
 * Created by daitian on 2017/11/9.
 */
public class UserLogin {
    private String userName;
    private String password;
    private String loginCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }
}
