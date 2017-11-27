package com.hiteamtech.uws.resultmapping.tpl;

/**
 * Created by BigK on 2017/9/21.
 */
public class ModuleList {

    private int id;
    private String name;
    private int status;
    private int sortNum;
    private int moduleId;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

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

    @Override
    public String toString() {
        return "ModuleList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", sortNum=" + sortNum +
                ", moduleId=" + moduleId +
                '}';
    }
}
