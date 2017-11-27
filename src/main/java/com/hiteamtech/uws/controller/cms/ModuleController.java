package com.hiteamtech.uws.controller.cms;

import com.hiteamtech.uws.resultmapping.tpl.Modules;
import com.hiteamtech.uws.service.cms.IModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created  on 2017/9/28.
 */
@Api(tags = "CMS-模块管理API文档")
@RestController
@RequestMapping("/cms")
public class ModuleController {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IModuleService iModuleService;

    @ApiOperation(value = "添加模块", notes = "分页查询模块列表，默认排序创建时间倒序")
    @PostMapping(value = "/module/addModule")
    public HttpStatus module(@RequestParam("name") String name, @RequestParam("appId") int appId,
                             @RequestParam("type") int type, @RequestParam("describe") String describe,
                             @RequestParam("status") boolean status
    ) {
        log.debug("增加模块");
        Modules modules = new Modules();
        modules.setAppId(appId);
        modules.setName(name);
        modules.setDescribe(describe);
        modules.setType(type);
        modules.setStatus(status);
        iModuleService.addModules(modules);
        return HttpStatus.OK;
    }

    @ApiOperation(value = "查询模块详情", notes = "查询模块详情信息")
    @GetMapping(value = "/module/{id}")
    public ResponseEntity getModule(@PathVariable("id") int id) {
        log.debug("根据模块id查询模块详情" + id);
        return iModuleService.findModuleById(id);
    }

    @ApiOperation(value = "删除指定模块", notes = "删除指定模块")
    @DeleteMapping(value = "/deleteModule/{id}")
    public HttpStatus delModule(@PathVariable("id") int id) {
        log.debug("根据模块ID删除模块" + id);
        try {
            return iModuleService.deleteModule(id);
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @ApiOperation(value = "分页查询指定app模块列表", notes = "分页查询模块列表，默认排序创建时间倒序")
    @GetMapping(value = "/modules/{appId}/{page}/{pageCount}")
    public ResponseEntity<Map> findModuleByAppId(@PathVariable("appId") int appId, @PathVariable("page") int page, @PathVariable("pageCount") int pageCount) {
        log.debug("根据appID获取模块列表:" + appId + "page:" + page + "pageCount:" + pageCount);
        return iModuleService.findModuleByAppId(appId, page, pageCount);
    }


    @ApiOperation(value = "获取banner列表", notes = "根据moduleId分页获取banner列表")
    @GetMapping(value = "/banners/{moduleId}/{page}/{pageCount}")
    public ResponseEntity<Map> findBannersByModuleId(@PathVariable("moduleId") int moduleId, @PathVariable("page") int page, @PathVariable("pageCount") int pageCount) {
        log.debug("根据moduleId获取banner列表:" + moduleId + "page:" + page + "pageCount:" + pageCount);
        Map m =null;
        try {
            m = iModuleService.findBannersByModuleId(moduleId, page, pageCount);
            if (m != null && m.size() > 0) {
                return new ResponseEntity<>(m, HttpStatus.OK);
            }
            return new ResponseEntity<>(m, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(m, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
