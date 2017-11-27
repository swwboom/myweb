package com.hiteamtech.uws.resultmapping.tpl;

import java.util.Date;

public class Article {
    private int id; //文章id
    private String author;//文章作者
    private String title; //文章标题
    private String content; //文章内容
    private Date createTime; //创建时间
    private int status; //文章状态
    private int fileType; //文章类型
    private int moduleListArticle; //文章所属模型列表
    private String imgUrl;  //文章图片地址
    private String videoUrl; //文章视频地址
    private int moudleListId; //模型列表id

    public int getMoudleListId() {
        return moudleListId;
    }

    public void setMoudleListId(int moudleListId) {
        this.moudleListId = moudleListId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public int getModuleListArticle() {
        return moduleListArticle;
    }

    public void setModuleListArticle(int moduleListArticle) {
        this.moduleListArticle = moduleListArticle;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

}
