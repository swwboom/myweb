package com.hiteamtech.uws.service.tplservice;
/**
 * Created by 刘旭 on 2017/9/25.
 *
 */

import com.hiteamtech.uws.commons.Config;
import com.hiteamtech.uws.dao.IArticleDao;
import com.hiteamtech.uws.dao.IModuleListDao;
import com.hiteamtech.uws.resultmapping.tpl.*;
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
public class ArticleService implements IArticleService {
    private  final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IArticleDao articleDao;
    @Autowired
    private IModuleListDao moduleListDao;


    @Override
    public ResponseEntity<Map> findArticleById(int articleId) {

        Map<String,Object> article = articleDao.findArticleById(articleId);
        List<Map<String,Object>> articleImages=articleDao.findImageListByArticleId(articleId);
        List<Map<String,Object>> articleVideos=articleDao.findVideoListByArticleId(articleId);
        if (article !=null ) {
            Map map=new HashMap();
            map.put("article",article);
            map.put("images",articleImages);
            map.put("videos",articleVideos);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Map> findQuestionById(int articleId) {

        Map<String,Object> article = articleDao.findArticleById(articleId);

        if (article !=null ) {
            List<Map<String,Object>> articleImages=articleDao.findImageListByArticleId(articleId);
            List<Map<String,Object>> articleVideos=articleDao.findVideoListByArticleId(articleId);
            Map map=new HashMap();
            map.put("question", article.get("title"));
            map.put("answer", article.get("content"));
            String coverUrl = Config.QUESTION_COVER_IMG;
            if(articleImages != null && articleImages.size()>0){
                coverUrl = (String) articleImages.get(0).get("url");
                articleImages.remove(articleImages.get(0));
            }
            map.put("images", articleImages);
            map.put("videos", articleVideos);
            map.put("coverImageUrl", coverUrl);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *
     *分页根据分类获取文章列表
     * */
    @Override
    public ResponseEntity<Map> listArticle(int moduleListId, int page,int pageCount) {
        int page1=setIndeByPage(page,pageCount);
        List<Map<String,Object>> articleList=articleDao.listArticles(moduleListId, page1,pageCount);
        long articleListCount = articleDao.countArticles(moduleListId);
        if(articleList !=null && articleList.size()>0){
            Map map =new HashMap();
            map.put("articleListCount",articleListCount);
            map.put("articleList",articleList);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Map moduleArticles(int moduleId, int page, int pageCount) {
        List<Map> articles= articleDao.moduleArticles(moduleId,setIndeByPage(page,pageCount),pageCount,Config.preUrl);
        int total=moduleArticlesTotal(moduleId);
        Map map = new HashMap();
        map.put("total",total);
        map.put("articles",articles);
        return map;

    }

    public int moduleArticlesTotal(int moduleId){
        return articleDao.moduleArticlesTotal(moduleId);
    }

    @Override
    public ResponseEntity updateArticle(int id,Article article){
        long result=articleDao.updateArticleById(id,article);
        if (result > 0) {
            return new ResponseEntity("'sucess'", HttpStatus.OK);
        } else {
            return new ResponseEntity("'false'", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public int setIndeByPage(int page,int pageCount){
        return (page-1)* pageCount;
    }
    /**
     *
     *根据模块ID获取模块所有分类下所有文章列表集合
     * */
    @Override
    public ResponseEntity<Map> findListByArticleListId(int moduleListId) {
        List<Map<String,Object>> articles = articleDao.findArticleListByModuleListId(moduleListId);
        if (articles != null && articles.size() > 0){
            Map map =new HashMap();
            map.put("articles",articles);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
