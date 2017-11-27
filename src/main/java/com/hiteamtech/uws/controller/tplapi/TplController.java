package com.hiteamtech.uws.controller.tplapi;

import com.hiteamtech.uws.resultmapping.tpl.Article;
import com.hiteamtech.uws.service.tplservice.IArticleService;
import com.hiteamtech.uws.service.tplservice.ITPLService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created on 2017/9/21.
 */
@Api(tags = "通用Web服务TPL Api文档")
@RestController
@RequestMapping("/tpl")
public class TplController {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ITPLService itplService;

    @Autowired
    private IArticleService iArticleService;


    @ApiOperation(value = "获取模块分类集合", notes = "根据模块ID获取模块下的分类集合")
    @GetMapping(value = "/moduleList/{moduleId}")
    public ResponseEntity<List> moduleList(@PathVariable("moduleId") int moduleId) {
        log.debug("请求模块分类集合，模块ID:" + moduleId);
        return itplService.findModuleList(moduleId);
    }

    @ApiOperation(value = "获取模块banner集合", notes = "根据模块ID获取模块下的banner集合")
    @GetMapping(value = "/banner/{moduleId}")
    public ResponseEntity<Map> getBanner(@PathVariable("moduleId") int moduleId) {
        log.debug("请求banner集合，模块ID:" + moduleId);
        return itplService.findBannerNode(moduleId);
    }


    @ApiOperation(value = "获取模块banner与分类集合", notes = "根据模块ID获取模块下的banner与分类集合")
    @GetMapping(value = "/modListBanner/{moduleId}")
    public ResponseEntity<Map> getModListBanner(@PathVariable("moduleId") int moduleId) {
        log.debug("请求banner与分类集合，模块ID:" + moduleId);
        return itplService.findBnnerAndTypeListByModuleId(moduleId);
    }


    @ApiOperation(value = "分页查询分类下的文章列表 ", notes = "根据列表分类ID获取分类下的文章列表集合")
    @GetMapping(value = "/listArticles/{moduleListId}/{page}/{pageCount}")
    public ResponseEntity<Map> listArticles(@PathVariable("moduleListId") int moduleListId, @PathVariable("page") int page, @PathVariable("pageCount") int pageCount) {
        log.debug("获取分类的文章集合，请求分类的模块id:" + moduleListId + "page:" + page + "pageCount:" + pageCount);
        return iArticleService.listArticle(moduleListId, page, pageCount);
    }

    @ApiOperation(value = "分页查询模块下的文章列表 ", notes = "根据模块ID分页查询模块下的文章列表")
    @GetMapping(value = "/moduleArticles/{moduleId}/{page}/{pageCount}")
    public ResponseEntity<Map> moduleArticles(@PathVariable("moduleId") int moduleId, @PathVariable("page") int page, @PathVariable("pageCount") int pageCount) {
        log.debug("分页查询模块下的文章列表，请求分类的模块id:" + moduleId + "page:" + page + "pageCount:" + pageCount);
        Map map = null;
        try {
            map = iArticleService.moduleArticles(moduleId, page, pageCount);
            if (map != null && map.size() > 0) {
                return new ResponseEntity<>(map, HttpStatus.OK);
            }
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "编辑文章 ", notes = "根据文章ID编辑文章")
    @PostMapping(value = "/updateArticles/{id}")
    public ResponseEntity updateArticleByArticleId(@PathVariable("id") int id,@RequestParam("author") String author,@RequestParam("title") String title,@RequestParam("content") String content){
        log.debug("编辑一个文章");
        Article article=new Article();
        article.setAuthor(author);
        article.setTitle(title);
        article.setContent(content);
        article.setCreateTime(new Date());
        return iArticleService.updateArticle(id,article);
    }



    @ApiOperation(value = "获取模块下的所有文章集合", notes = "根据模块ID获取模块所有分类下所有文章列表集合")
    @GetMapping(value = "/moduleArticles/{moduleListId}")
    public ResponseEntity<Map> moduleArticles(@PathVariable("moduleListId") int moduleListId) {
        log.debug("获取模块文章集合，请求模块id:" + moduleListId);
        return iArticleService.findListByArticleListId(moduleListId);
    }

    @ApiOperation(value = "获取文章详情", notes = "根据文章ID获取文章详情数据")
    @GetMapping(value = "/article/{articleId}")
    public ResponseEntity<Map> article(@PathVariable("articleId") int articleId) {
        log.debug("获取文章详情，请求文章id:" + articleId);
        return iArticleService.findArticleById(articleId);
    }

    @ApiOperation(value = "获取问题详情", notes = "根据问题ID获取问题详情数据")
    @GetMapping(value = "/question/{questionId}")
    public ResponseEntity<Map> question(@PathVariable("questionId") int questionId) {
        log.debug("获取问题详情，请求文章id:" + questionId);
        return iArticleService.findQuestionById(questionId);
    }


}
