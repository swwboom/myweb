package com.hiteamtech.uws.service.cms.impl;
import com.hiteamtech.uws.dao.ModuleDao;
import com.hiteamtech.uws.resultmapping.tpl.Modules;
import com.hiteamtech.uws.service.cms.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModuleService implements IModuleService {
    @Autowired
    private ModuleDao moduleDao;
    @Override
    public HttpStatus deleteModule(int id){
        moduleDao.deleteModule(id);
        return HttpStatus.OK;
    }
    @Override
    public ResponseEntity<Map> addModules(Modules modules) {
        int result = moduleDao.addModules(modules);
        if(result>0){
            return new ResponseEntity("success", HttpStatus.OK);
        }else{
            return new ResponseEntity("error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseEntity<Map> findModuleById(int id) {
        List<Map<String, Object>> list =moduleDao.findModuleById(id);
        if (list != null && list.size() > 0) {
            Map  map= new HashMap();
            map.put("modules",list);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public ResponseEntity<Map> findModuleByAppId(int appId, int page,int pageCount){
        int page1=setIndeByPage(page,pageCount);
        List<Map<String, Object>> list= moduleDao.findModuleByAppId(appId,page1,pageCount);
        long modulesCount=moduleDao.findModuleCount(appId);
        if (list != null && list.size() > 0) {
            Map map =new HashMap();
            map.put("modulesCount",modulesCount);
            map.put("moduleInfo",list);
            return new ResponseEntity(map, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Map findBannersByModuleId(int moduleId, int page, int pageCount) {
        Map map = new HashMap();
        int total =moduleDao.findBannerNumByModuleId(moduleId);
        List<Map> m =moduleDao.findBannersByModuleId(moduleId,setIndeByPage(page,pageCount),pageCount);
        map.put("total",total);
        map.put("banners",m);
        return map;
    }

    public int setIndeByPage(int page,int pageCount){
        return (page-1)* pageCount;
    }
}
