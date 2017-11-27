package com.hiteamtech.uws.controller.tplapi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created on 2017/9/30.
 */
@Api(tags = "APP端调用")
@Controller
public class TPLRoutes {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "TPL路由转发", notes = "根据模块ID路由到目标TPL模板")
    @GetMapping(value = "/module/{moduleId}/{tplId}")
    public String router(@PathVariable("moduleId") int moduleId,@PathVariable("tplId") int tplId){
        log.info("TPL路由转发,moduleId:"+moduleId+",TPLID:"+tplId);
        return "redirect:http://test.tpl.ddbes.com/#/?moduleId="+moduleId+"&tplId="+tplId;
    }
}
