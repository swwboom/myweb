package com.hiteamtech.uws.service.cms.impl;

import com.hiteamtech.uws.dao.IArticleDao;
import com.hiteamtech.uws.resultmapping.tpl.Article;
import com.hiteamtech.uws.service.cms.ICArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CArticleService implements ICArticleService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IArticleDao articleDao;

    /**
     * 添加一篇文章
     * */
    @Override
    public ResponseEntity<Map> addArticle(Article article) {

         int  result=articleDao.addArticle(article);
            if (result>0){
                return new ResponseEntity("success",HttpStatus.OK);
            }else{
                return new ResponseEntity("error",HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    /**
     * 删除一篇文章
     * */
    @Override
    public ResponseEntity<String> deleteArticleById(int articleId) {
        if( articleId != 0){
            articleDao.deleteArticleById(articleId);
            return new ResponseEntity<>("success", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 分页获取当前用户的所有文章
     * */
    @Override
    public ResponseEntity<Map> listArticleByPage(int userId,int page,int pageCount) {
        if(userId != 0){
            int page1=setIndeByPage(page,pageCount);
            List<Map<String,Object>> articlesList = articleDao.findAllArticleByUserId(userId,page1,pageCount);
            long articlesCount=articleDao.countArticleByUserId(userId);
            Map map =new HashMap();
            map.put("articlesCount",articlesCount);
            map.put("articlesList",articlesList);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public int setIndeByPage(int page,int pageCount){
        return (page-1)* pageCount;
    }

    @Override
    public ResponseEntity<Map> listArticleByUserId(int userId){
        List<Map<String,Object>> list=articleDao.findArticleByUserId(userId);
        Map map =new HashMap();
        map.put("allArticles",list);
        if (map.size()>0){
            return new ResponseEntity<>(map, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
