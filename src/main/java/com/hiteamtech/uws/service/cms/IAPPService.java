package com.hiteamtech.uws.service.cms;

import com.hiteamtech.uws.resultmapping.cms.AppInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by BigK on 2017/10/13.
 */
public interface IAPPService {

    /**
     * Updata by Wailliam Shaw on 2017/9/29.
     * 根据用户ID查找 APP，不分页显示。
     */
    List findAllAppsInfoByUserId(int userId);
    /**
     * Updata by Wailliam Shaw on 2017/9/29.
     * 根据用户ID查找 APP，分页显示。
     */
    List findAppsByUserId(int userId, int page, int pageCount);
    /**
     * Updata by Wailliam Shaw on 2017/9/29.
     * 根据userID查找 产品总数。
     */
    long findAppsCount(int userId);
    /**
     * Updata by Wailliam Shaw on 2017/9/29.
     * 根据产品ID查找 产品详情。
     */
    ResponseEntity<Map> findAppByUserId(int id);
    /**
     * Updata by Wailliam Shaw on 2017/9/29.
     * 根据产品ID删除产品。
     */
    public HttpStatus deleteApp(int id);

    /**
     * Updata by di on 2017/9/29.
     * 增加产品。
     */
    ResponseEntity<Map> addAppInfo(AppInfo appInfo);

}
