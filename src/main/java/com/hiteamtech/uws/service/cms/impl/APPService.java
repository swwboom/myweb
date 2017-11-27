package com.hiteamtech.uws.service.cms.impl;

import com.hiteamtech.uws.dao.AppsDao;
import com.hiteamtech.uws.resultmapping.cms.AppInfo;
import com.hiteamtech.uws.service.cms.IAPPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BigK on 2017/10/13.
 */
@Service
public class APPService implements IAPPService {

    private  final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppsDao appsDao;

    @Override
    public List findAppsByUserId(int userId, int page, int pageCount){
        int page1=setIndeByPage(page,pageCount);
        List list=  appsDao.findAppInfoByUserId(userId,page1,pageCount);
        return list;
    }
    public int setIndeByPage(int page,int pageCount){
        return (page-1)* pageCount;
    }
    @Override
    public ResponseEntity<Map> findAppByUserId(int id) {
        Map<String,Object> list = (Map<String, Object>) appsDao.findAppByUserId(id);
        Map map=new HashMap();
        map.put("app",list);
        if (list != null && list.size() > 0) {
            return new ResponseEntity(list, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public HttpStatus deleteApp(int id){
        appsDao.deleteApp(id);
        return HttpStatus.OK;
    }

    @Override
    public ResponseEntity<Map> addAppInfo(AppInfo appInfo) {
        int result = appsDao.addAppInfo(appInfo);
        if(result>0){
            return new ResponseEntity("success", HttpStatus.OK);
        }else{
            return new ResponseEntity("error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public long findAppsCount(int userId){
        long result=appsDao.countAppInfoByUserId(userId);

            return result;
    }
    @Override
    public List findAllAppsInfoByUserId(int userId){
        List list = appsDao.findAllAppsByUserId(userId);
        return list;
    }
}
