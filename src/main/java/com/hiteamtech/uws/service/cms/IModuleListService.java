package com.hiteamtech.uws.service.cms;

import com.hiteamtech.uws.resultmapping.tpl.ModuleList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IModuleListService{
    /**
     * 添加模块分类
     */
    ResponseEntity<Map> addModuleList(ModuleList moduleList);
    /**
     *查询指定模块的分类列表，默认排序创建时间倒序
     */
    ResponseEntity<List> findModuleArticleListByModuleId(int moduleId);
    /**
     * 删除模块分类
     */
    public HttpStatus deleteModuleList(int id);
}
