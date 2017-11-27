package com.hiteamtech.uws.controller.cms;

import com.hiteamtech.uws.commons.Config;
import com.hiteamtech.uws.resultmapping.cms.AppInfo;
import com.hiteamtech.uws.resultmapping.cms.LoginInfo;
import com.hiteamtech.uws.service.cms.IAPPService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created on 2017/9/28.
 */
@Api(tags = "CMS-应用管理API文档")
@RestController
@RequestMapping("/cms")
public class AppController {


    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IAPPService iappService;

    @ApiOperation(value = "添加产品", notes = "用户添加产品")
    @PostMapping(value = "/app/addApp")
    public HttpStatus addApp(@RequestParam("appName") String appName,
                             @RequestParam("appIntroduction") String appIntroduction,
                             @RequestParam("status") boolean status,
                              HttpServletRequest request  ){
        log.debug("增加产品");
        LoginInfo loginInfo = (LoginInfo) request.getSession().getAttribute(Config.USER_SESSION_KEY);
        AppInfo appInfo=new AppInfo();
        appInfo.setAppName(appName);
        appInfo.setUserId(loginInfo.getUserId());
        appInfo.setAppIntroduction(appIntroduction);
        appInfo.setCreateTime(new Date());
        appInfo.setStatus(status);
        iappService.addAppInfo(appInfo);
        return HttpStatus.OK;
    }

    @ApiOperation(value = "分页查询我的产品", notes = "分页查询我的产品（只查询状态正常的）")
    @GetMapping(value = "/apps/{page}/{pageCount}")
    public ResponseEntity<Map> findAppsByUserId( @PathVariable("page") int page, @PathVariable("pageCount") int pageCount,HttpServletRequest request){
        log.debug("根据用户ID获取我的产品:" + "page:" + page+"pageCount:"+ pageCount);
        LoginInfo loginInfo = (LoginInfo) request.getSession().getAttribute(Config.USER_SESSION_KEY);
        Map map=new HashMap();
        map.put("appCount",iappService.findAppsCount(loginInfo.getUserId()));
        map.put("appInfo",iappService.findAppsByUserId(loginInfo.getUserId(),page,pageCount));
        return new  ResponseEntity<>(map,HttpStatus.OK);
    }

    @ApiOperation(value = "查询我的所有产品", notes = "查询我的所有产品详情")
    @GetMapping(value = "/allApps")
    public ResponseEntity<Map> getAppsByUserId(HttpServletRequest request){
            LoginInfo loginInfo = (LoginInfo) request.getSession().getAttribute(Config.USER_SESSION_KEY);
            log.debug("根据用户ID获取我所有的APP"+loginInfo.getUserId());
            Map map=new HashMap();
            map.put("appsCount",iappService.findAppsCount(loginInfo.getUserId()));
            map.put("apps",iappService.findAllAppsInfoByUserId(loginInfo.getUserId()));
            if (map.size()>0){
                return new ResponseEntity<>(map,HttpStatus.OK);
            }else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
    }


    @ApiOperation(value = "查询我的产品", notes = "查询我的产品详情")
    @GetMapping(value = "/app/{id}")
    public ResponseEntity<Map> getApp(@PathVariable("id") int id){
        log.debug("根据产品ID获取我的产品信息"+id);
        return iappService.findAppByUserId(id);
    }

    @ApiOperation(value = "删除我的产品", notes = "状态为正常改为2删除状态")
    @DeleteMapping(value = "/deleteApp/{id}")
    public HttpStatus delApp(@PathVariable("id") int id){
        log.debug("根据产品ID删除产品"+id);
        try {
            return iappService.deleteApp(id);
        }catch (Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
