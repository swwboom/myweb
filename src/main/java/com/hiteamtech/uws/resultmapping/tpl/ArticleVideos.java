package com.hiteamtech.uws.resultmapping.tpl;

/**
 * Created by BigK on 2017/9/22.
 */
public class ArticleVideos {

    private String videoUrl;//视频链接地址

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String toString() {
        return "ArticleVideos{" +
                "videoUrl='" + videoUrl + '\'' +
                '}';
    }
}
