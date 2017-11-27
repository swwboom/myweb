package com.hiteamtech.uws.controller.cms;

import com.hiteamtech.uws.resultmapping.tpl.ModuleList;
import com.hiteamtech.uws.service.cms.IModuleListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created on 2017/9/28.
 */
@Api(tags = "CMS-模块分类管理API文档")
@RestController
@RequestMapping("/cms")
public class ModuleListController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IModuleListService moduleListService;

    @ApiOperation(value = "查询指定模块的分类列表", notes = "查询指定模块的分类列表，默认排序创建时间倒序")
    @GetMapping(value = "/moduleList/{moduleId}")
    public ResponseEntity<List> moduleList(@PathVariable("moduleId") int moduleId){
        log.info("模块id：" + moduleId);
        return moduleListService.findModuleArticleListByModuleId(moduleId);
    }

    @ApiOperation(value = "给模块添加分类", notes = "给模块添加分类")
    @PostMapping(value = "/addModuleList/{name}/{moduleId}/{sortNum}")
    public ResponseEntity<Map> addModuleList(@PathVariable("name") String name,@PathVariable("moduleId") int moduleId, @PathVariable("sortNum") int sortNum){
        ModuleList moduleList = new ModuleList();
        moduleList.setName(name);
        moduleList.setStatus(1);
        moduleList.setModuleId(moduleId);
        moduleList.setSortNum(sortNum);
        return moduleListService.addModuleList(moduleList);
    }

    @ApiOperation(value = "删除模块分类", notes = "删除模块分类")
    @DeleteMapping(value = "delModuleList/{id}")
    public HttpStatus delModuleList(@PathVariable("id") int id){
        log.debug("根据模块分类ID删除模块分类"+id);
        try {
            return moduleListService.deleteModuleList(id);
        }catch (Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
