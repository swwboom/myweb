package com.hiteamtech.uws.controller.cms;

import com.hiteamtech.uws.commons.Config;
import com.hiteamtech.uws.resultmapping.cms.LoginInfo;
import com.hiteamtech.uws.resultmapping.tpl.Article;
import com.hiteamtech.uws.service.cms.ICArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.Map;

/**
 * Created on 2017/9/28.
 */
@Api(tags = "CMS-文章管理API文档")
@RestController
@RequestMapping("/cms")
public class ArticleController {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ICArticleService icArticleService;
    /**
     * @param title
     * @param author
     * @param content
     * */
    @ApiOperation(value = "添加文章", notes = "添加文章")
    @PostMapping(value = "/article/addArticle")
    public void addArticle(@RequestParam("author") String author,@RequestParam("title") String title,@RequestParam("content") String content,@RequestParam("fileType") int fileType,@RequestParam("status") int status){
        Article article=new Article();
        article.setAuthor(author);
        article.setTitle(title);
        article.setContent(content);
        article.setCreateTime(new Date());
        article.setFileType(fileType);
        article.setStatus(status);
        icArticleService.addArticle(article);
    }

    /**
     * @param articleId
     * */
    @ApiOperation(value = "删除文章", notes = "删除文章")
    @DeleteMapping(value = "/article/delArticle/{articleId}")
    public void delArticle(@RequestParam("articleId") int articleId){
        icArticleService.deleteArticleById(articleId);
    }
    /**
     * @param page
     * @param pageCount
     * */
    @ApiOperation(value = "分页获取我的文章列表", notes = "获取当前用户的所有文章")
    @GetMapping(value = "/articles/listUserArticleByPage/{page}/{pageCount}")
    public ResponseEntity<Map> listUserArticleByPage( @PathVariable("page") int page, @PathVariable("pageCount") int pageCount, HttpServletRequest request){
        LoginInfo loginInfo = (LoginInfo) request.getSession().getAttribute(Config.USER_SESSION_KEY);
        return icArticleService.listArticleByPage(loginInfo.getUserId(),page,pageCount);
    }

    @ApiOperation(value = "不分页获取我的文章列表", notes = "获取当前用户的所有文章")
    @GetMapping(value = "/articles/listUserArticleByUserId")
    public ResponseEntity<Map> listUserArticlesByUserId(HttpServletRequest request){
        LoginInfo loginInfo = (LoginInfo) request.getSession().getAttribute(Config.USER_SESSION_KEY);
        return icArticleService.listArticleByUserId(loginInfo.getUserId());
    }

}
