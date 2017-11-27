package com.hiteamtech.uws.controller.cms;

import com.hiteamtech.uws.resultmapping.tpl.BannerNode;
import com.hiteamtech.uws.service.tplservice.ITPLService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created on 2017/9/28.
 */

@Api(tags = "CMS-Banner信息管理API文档")
@RestController
@RequestMapping("/cms")
public class BannerController {
    @Autowired
    private ITPLService itplService;
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "删除banner节点", notes = "根据bannerID，获取banner集合")
    @DeleteMapping(value = "/deleteBanner/{id}")
    public HttpStatus delBanner(@PathVariable("id") int id){
        log.debug("根据bannerNode删除banner节点"+id);
        try {
            return itplService.deleteBanner(id);
        }catch (Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @ApiOperation(value = "添加banner节点", notes = "给指定banner添加banner节点")
    @PostMapping(value = "/banner")
    public HttpStatus addBanner(@RequestParam("imgUrl") String imgUrl, @RequestParam("title") String title,
                                @RequestParam("jumpUrl") String jumpUrl,
                                @RequestParam("moduleId")int bannerId,
                                @RequestParam("status") int status,
                                @RequestParam("sortNum") int sortNum, @RequestParam("groundingTime") String groundingTime,@RequestParam("undercarriageTime") String undercarriageTime){
        log.debug("增加一个banner节点");
        BannerNode bannerNode =new BannerNode();
        bannerNode.setImgUrl(imgUrl);
        bannerNode.setTitle(title);
        bannerNode.setJumpUrl(jumpUrl);
        bannerNode.setBannerId(bannerId);
        bannerNode.setStatus(status);
        bannerNode.setCreateTime(new Date());
        bannerNode.setSortNum(sortNum);
        bannerNode.setGroundingTime(groundingTime);
        bannerNode.setUndercarriageTime(undercarriageTime);
        itplService.addBanner(bannerNode);
        return HttpStatus.OK;
    }

    @ApiOperation(value = "编辑banner节点", notes = "编辑banner节点")
    @PostMapping(value = "/updateBanner/{id}")
    public ResponseEntity updateBanner(@PathVariable("id")int id, String imgUrl, String title,
                              String jumpUrl,
                              int status,
                            int sortNum,  String groundingTime, String undercarriageTime){

        log.debug("编辑一个banner节点");
        BannerNode bannerNode =new BannerNode();
        bannerNode.setImgUrl(imgUrl);
        bannerNode.setTitle(title);
        bannerNode.setJumpUrl(jumpUrl);
        bannerNode.setStatus(status);
        bannerNode.setCreateTime(new Date());
        bannerNode.setSortNum(sortNum);
        bannerNode.setGroundingTime(groundingTime);
        bannerNode.setUndercarriageTime(undercarriageTime);
        return itplService.updateBanner(id,bannerNode);
    }

}
