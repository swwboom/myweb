package com.hiteamtech.uws.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by BigK on 2017/9/28.
 */
public class Config {

    /**
     * 问题详情页默认封面图
     */
    public static final String QUESTION_COVER_IMG = "http://cdn.joinunow.com/FuqK60nOz5uENQKqXgVUqc1b7ItZ";

    /**
     * PBKDF2算法迭代计数
     */
    public static final int PBKDF2_ITERATIONS = 10000;

    /**
     * cdn 基础url
     */
    public static final String CDN_BASE_URL = "http://cdn.joinunow.com/";

    /**
     * SESSION KEY
     */
    public static final String USER_SESSION_KEY = "USERINFO";

    /**
     * 封装，不需要过滤的list列表
     */
    public static List<Pattern> patterns = new ArrayList<Pattern>();

    /**
     * 图片url前缀
     */
    public static final String preUrl="http://";

}
