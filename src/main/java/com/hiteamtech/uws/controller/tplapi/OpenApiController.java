package com.hiteamtech.uws.controller.tplapi;

import com.hiteamtech.uws.service.tplservice.ITPLService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created  on 2017/9/29.
 */

@Api(tags = "通用Web服务开放接口 Api文档")
@RestController
@RequestMapping("/openApi")
public class OpenApiController {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ITPLService itplService;
    @ApiOperation(value = "根据bannerID，获取banner集合", notes = "根据bannerID，获取banner集合")
    @GetMapping(value = "/banner/{bannerId}")
    public ResponseEntity<List> getBannerByBannerId(@PathVariable("bannerId") int bannerId){
        log.debug("请求banner集合，BannerID:"+bannerId);
        return itplService.getBannerByBannerId(bannerId);
    }
}
