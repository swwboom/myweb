package com.hiteamtech.uws.service.tplservice;

import com.hiteamtech.uws.resultmapping.tpl.Article;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by 刘旭 on 2017/9/25.
 *
 */


public interface IArticleService {
    /**
     * 获取文章详情页
     * */
    ResponseEntity<Map> findArticleById(int articleId);

    /**
     * 获取问题详情
     * @param articleId
     * @return
     */
    ResponseEntity<Map> findQuestionById(int articleId);

    /**
     * 根据模块ID获取模块所有分类下所有文章列表集合
     * */
    ResponseEntity<Map> findListByArticleListId(int moduleListId);

    /**
     * 根据列表分类ID获取分类下的文章列表集合
     * */
    ResponseEntity<Map> listArticle(int moduleListId,int page,int pageCount);
    /**
     * 编辑文章列表
     * */
    ResponseEntity updateArticle(int id,Article article);

    /**
     * 根据模块ID分页查询模块下的文章列表
     * @param moduleId
     * @param page
     * @param pageCount
     * @return
     */
    Map moduleArticles(int moduleId, int page, int pageCount);
}
