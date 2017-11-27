package com.hiteamtech.uws.service.cms;

import com.hiteamtech.uws.resultmapping.tpl.Article;
import org.springframework.http.ResponseEntity;
import java.util.Map;

public interface ICArticleService {
    /**
     * 添加一篇文章
     * */
    ResponseEntity<Map> addArticle(Article article);
    /**
     * 删除一篇文章
     * */
    ResponseEntity<String> deleteArticleById(int articleId);
    /**
     *分页查看用户的所有文章
     * */
    ResponseEntity<Map> listArticleByPage(int userId,int page,int pageCount);
    /**
     *不分页查看用户的所有文章
     * */
    ResponseEntity<Map> listArticleByUserId(int userId);

}
