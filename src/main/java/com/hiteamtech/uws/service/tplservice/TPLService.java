package com.hiteamtech.uws.service.tplservice;


import com.hiteamtech.uws.dao.BannerNodeDao;
import com.hiteamtech.uws.dao.IModuleListDao;
import com.hiteamtech.uws.resultmapping.tpl.BannerNode;
import com.hiteamtech.uws.resultmapping.tpl.ModuleList;
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
 * Created by BigK on 2017/9/21.
 */
@Service
public class TPLService implements ITPLService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IModuleListDao moduleListDao;

    @Autowired
    private BannerNodeDao bannerNodeDao;


    @Override
    public ResponseEntity<List> findModuleList(int moduleId) {
        List<Map<String, Object>> list = moduleListDao.findListByModulesId(moduleId);
        if (list != null && list.size() > 0) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public ResponseEntity<Map> findBannerNode(int moduleId) {
        List<Map<String, Object>> list = bannerNodeDao.findBannerNodeBymoduleId(moduleId);
        if (list != null && list.size() > 0) {
            Map map=new HashMap();
            map.put("banner",list);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Map> findBnnerAndTypeListByModuleId(int moduleId) {
        List<Map<String, Object>> list = bannerNodeDao.findBannerNodeBymoduleId(moduleId);
        List<Map<String, Object>> list1 = moduleListDao.findListByModulesId(moduleId);
        Map map = new HashMap() ;
        map.put("banerList",list);
        map.put("moduleList",list1);
        if (map != null && list.size() > 0) {
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public ResponseEntity<List> getBannerByBannerId(int moduleId) {
        List<Map<String, Object>> list = bannerNodeDao.findBannerNodeBymoduleId(moduleId);
        if (list != null && list.size() > 0) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Map> addBanner(BannerNode bannerNode){
        int result =bannerNodeDao.addBanner(bannerNode);
        if (result>0){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseEntity updateBanner(int id,BannerNode bannerNode) {
        long result = bannerNodeDao.updateBannerByBannerId(id,bannerNode);
        if (result > 0) {
            return new ResponseEntity("'sucess'", HttpStatus.OK);
        } else {
            return new ResponseEntity("'false'", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public HttpStatus deleteBanner(int id){
        bannerNodeDao.deleteBanner(id);
        return HttpStatus.OK;
    }
}