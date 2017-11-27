package com.hiteamtech.uws.resultmapping.tpl;

public class Modules {
    private int id;
    private String name;
    private int appId;
    private int type;
    private String describe;
    private boolean status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Modules{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", appId=" + appId +
                ", type=" + type +
                ", describe='" + describe + '\'' +
                ", status=" + status +
                '}';
    }
}
