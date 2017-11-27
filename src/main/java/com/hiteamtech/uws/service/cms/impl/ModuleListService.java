package com.hiteamtech.uws.service.cms.impl;

import com.hiteamtech.uws.dao.IModuleListDao;
import com.hiteamtech.uws.resultmapping.tpl.ModuleArticleList;
import com.hiteamtech.uws.resultmapping.tpl.ModuleList;
import com.hiteamtech.uws.service.cms.IModuleListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class ModuleListService implements IModuleListService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IModuleListDao moduleListDao;

    @Override
    public ResponseEntity<Map> addModuleList(ModuleList moduleList) {
        int result = moduleListDao.addModuleList(moduleList);
        if(result>0){
            return new ResponseEntity("success", HttpStatus.OK);
        }else{
            return new ResponseEntity("error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List> findModuleArticleListByModuleId(int moduleId) {
        if ( moduleId != 0){
            List<Map<String,Object>> moduleLists = moduleListDao.findModuleArticleListByModuleId(moduleId);
            return new ResponseEntity(moduleLists,HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public HttpStatus deleteModuleList(int id){
        moduleListDao.deleteModuleList(id);
        return HttpStatus.OK;
    }
}
