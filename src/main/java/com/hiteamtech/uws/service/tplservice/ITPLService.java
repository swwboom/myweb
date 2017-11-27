package com.hiteamtech.uws.service.tplservice;

import com.hiteamtech.uws.resultmapping.tpl.BannerNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;


/**
 * Created by BigK on 2017/9/21.
 */
/**
 * Update by William shaw on 2017/9/22.
 * 功能：增加查找banner的方法，增加查找banner与分类集合的方法
 */
public interface ITPLService {

    ResponseEntity<List> findModuleList(int moduleId);
    /**
     *  作者： William shaw on 2017/9/25.
     *  功能：根据ID获取banner的Service
     */
    ResponseEntity<Map> findBannerNode(int moduleId);
    /**
     *  作者： William shaw on 2017/9/25.
     *  功能：根据模块ID获取banner与分类的集合的Service
     */
    ResponseEntity<Map> findBnnerAndTypeListByModuleId(int moduleId);
    /**
     *  作者： William shaw on 2017/9/29.
     *  功能：根据bannerID获取banner
     */
    ResponseEntity<List> getBannerByBannerId(int bannerId);
    /**
     *  作者： William shaw on 2017/9/29.
     *  功能：增加一个banner节点
     */
    ResponseEntity<Map> addBanner(BannerNode bannerNode);
    /**
     * Updata by Wailliam Shaw on 2017/9/29.
     * 根据产品ID删除产品。
     */
    public HttpStatus deleteBanner(int id);

     ResponseEntity updateBanner(int id,BannerNode bannerNode);
}
