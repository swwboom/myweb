package com.hiteamtech.uws.dao;

import com.hiteamtech.uws.commons.Config;
import com.hiteamtech.uws.resultmapping.tpl.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface IArticleDao {

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "author", column = "author"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "status", column = "status"),
            @Result(property = "fileType", column = "file_type"),
            @Result(property = "moduleListArticle", column = "moduleListArticle"),
            @Result(property = "pic", column = "imgUrl"),
            @Result(property = "videoUrl", column = "videoUrl")
    })
    /**
     * Created by 刘旭 on 2017/9/25.
     * 文章详情页
     */
    @Select("SELECT id,author,title,content,create_time as createTime FROM article where id = #{articleId}")
    Map<String, Object> findArticleById(@Param("articleId") int articleId);

    /**
     * Created by 刘旭 on 2017/9/25.
     * 分页根据模块id获取分类下的文章列表集合
     */
    @Select("select id,author,title,create_time createTime from article where id in( select module_list_article from module_list_article where module_list_id in ( select id from module_list where module_id = #{moduleId})) order by id limit #{page},#{pageCount}")
    List<Map<String, Object>> listArticles(@Param("moduleId") int moduleId, @Param("page") int page1, @Param("pageCount") int pageCount);

    /**
     * Created by Wailliam Shaw on 2017/9/25.
     * 根据模块id获取分类下的文章列表总数
     */
    @Select("SELECT count(*) from article WHERE status = 1 and id in  ( select module_list_article from module_list_article where module_list_id in ( select id from module_list where module_id = #{moduleId})) ")
    long countArticles(@Param("moduleId") int moduleId);

    /**
     * create by 刘旭 on 2017/9/22
     * 根据modulelistArticle查询所有的article
     */
    @Select("select id,author,title,create_time createTime from article where id in (select module_list_article from module_list_article where module_list_id =#{moduleListId})")
    List<Map<String, Object>> findArticleListByModuleListId(@Param("moduleListId") int moduleListId);

    /**
     * 根据文章id获取图片集合
     */
    @Select("select CONCAT(\"" + Config.CDN_BASE_URL + "\",file_name) as url from article_files where article_id = #{articleId} and file_type = 1 ORDER BY sort_num  ")
    List<Map<String, Object>> findImageListByArticleId(@Param("articleId") int articleId);

    /**
     * 根据文章id获取视频地址集合
     */
    @Select("select file_name as url from article_files where article_id = #{articleId} and file_type = 2 ORDER BY sort_num  ")
    List<Map<String, Object>> findVideoListByArticleId(@Param("articleId") int articleId);

    /**
     * 添加一篇文章
     */
    @Insert("insert into article (author,title,content,file_type,create_time,status) values(#{article.author},#{article.title},#{article.content},#{article.fileType},#{article.createTime},#{article.status})")
    @Options(useGeneratedKeys = true, keyProperty = "article.id")
    int addArticle(@Param("article") Article article);

    /**
     * 删除一篇文章
     */
    @Update("update article set status = 2 where id = #{articleId}")
    Boolean deleteArticleById(@Param("articleId") int articleId);

    /**
     * 分页获取用户的所有文章
     */
    @Select("Select id,author,title,content,file_type as fileType from article where id in (select module_list_article from module_list_article where module_list_id in (Select id from module_list where module_id in( Select id from modules where app_id in ( Select  id from app_info where user_id = #{userId}))))AND status = 1 ORDER BY id LIMIT #{page},#{pageCount}")
    List<Map<String, Object>> findAllArticleByUserId(@Param("userId") int userId, @Param("page") int page1, @Param("pageCount") int pageCount);
    /**
     * 不分页获取用户的所有文章
     */
    @Select("Select id,author,title,content,file_type as fileType from article where id in (select module_list_article from module_list_article where module_list_id in (Select id from module_list where module_id in( Select id from modules where app_id in ( Select  id from app_info where user_id = #{userId}))))AND status = 1")
    List<Map<String,Object>>findArticleByUserId(@Param("userId") int userId);
    /**
     * 根据模块ID分页查询模块下的文章列表
     */
    @Select("SELECT * FROM (SELECT a.id,a.author,a.title,DATE_FORMAT(a.create_time,\"%Y年%m月%d日 %H:%i\") createTime,a.file_type fileType,CONCAT(#{preUrl},IFNULL(af.file_name,\"\")) fileName\n" +
            "FROM module_article ma\n" +
            "LEFT JOIN article a ON ma.article_id=a.id\n" +
            "LEFT JOIN article_files af ON af.article_id=a.id\n" +
            "WHERE ma.modules_id=#{moduleId} AND a.status=1\n" +
            "ORDER BY a.create_time DESC,af.file_type DESC,af.sort_num ASC\n" +
            ") b GROUP BY b.id\n" +
            "LIMIT #{page},#{pageCount}\n" +
            "\n")
    List<Map> moduleArticles(@Param("moduleId") int moduleId, @Param("page") int page, @Param("pageCount") int pageCount, @Param("preUrl") String preUrl);
    /**
     *更新文章
     */
    @Update("UPDATE article SET author=#{article.author},title=#{article.title},content=#{article.content} WHERE id= #{id}")
    long updateArticleById(@Param("id") int id,@Param("article") Article article);
    /**
     * 获取用户的所有文章总数
     */
    @Select("SELECT COUNT(*) FROM article where id in (select module_list_article from module_list_article where module_list_id in (Select id from module_list where module_id in( Select id from modules where app_id in ( Select  id from app_info where user_id = #{userId}))))AND status = 1 ")
    long countArticleByUserId(@Param("userId") int userId);

    /**
     * 获取模板下文章总数
     */
    @Select("SELECT count(ma.id) \n" +
            "FROM module_article ma\n" +
            "LEFT JOIN article a ON ma.article_id=a.id\n" +
            "WHERE ma.modules_id=#{moduleId} AND a.status=1\n")
    int moduleArticlesTotal(@Param("moduleId")int moduleId);
}
