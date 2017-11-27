package com.hiteamtech.uws.dao;

import com.hiteamtech.uws.resultmapping.tpl.BannerNode;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Map;


/**
 * Created by William shaw on 2017/9/22.
 * 根据数据库查询，映射出图片链接与跳转链接
 */

@Mapper
public interface BannerNodeDao {
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "imgUrl", column = "img_url"),
            @Result(property = "jumpUrl", column = "jump_url")
    })
    @Select("SELECT banner_node.id,img_url,jump_url\n" +
            "FROM banner_node\n" +
            "LEFT JOIN module_banner ON module_banner.banner_id = banner_node.banner_id\n" +
            "WHERE status = 1 and module_banner.modules_id=#{modulesId}")
    List<Map<String,Object>> findBannerNodeBymoduleId(@Param("modulesId") int modulesId);


    /**
     * Update by William shaw on 2017/9/29.
     * 根据bannerID映射出图片链接与跳转链接
     */
    @Results({
            @Result(property = "imgUrl", column = "img_url"),
            @Result(property = "jumpUrl", column = "jump_url")
    })
    @Select("SELECT id,img_url as imgUrl,jump_url as jumpUrl FROM banner_node WHRER status = 1 AND banner_id =#{bannerId} order by sort_num" )
    List<BannerNode> getBannerByBannerId(@Param("bannerId") int bannerId);
    /**
     * Update by William shaw on 2017/9/29.
     * 增加一个banner节点
     */
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "imgUrl", column = "img_url"),
            @Result(property = "title" , column = "title"),
            @Result(property = "jumpUrl", column = "jump_url"),
            @Result(property = "bannerId",column = "banner_id"),
            @Result(property = "status",column = "status"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "sortNum",column = "sort_num"),
            @Result(property = "groundingTime",column = "grounding_time"),
            @Result(property = "undercarriageTime",column = "undercarriage_time")
    })
    @Insert({"INSERT INTO banner_node (img_url,title,jump_url,banner_id,status,create_time,sort_num,grounding_time,undercarriage_time) VALUES (#{bannerNode.imgUrl},#{bannerNode.title},#{bannerNode.jumpUrl},#{bannerNode.bannerId},#{bannerNode.status},#{bannerNode.createTime},#{bannerNode.sortNum},#{bannerNode.groundingTime},#{bannerNode.undercarriageTime})"})
    @Options(useGeneratedKeys = true, keyProperty = "banner_node.id")
    int addBanner(@Param("bannerNode") BannerNode bannerNode);

    @Update("UPDATE banner_node SET img_url=#{bannerNode.imgUrl},title=#{bannerNode.title},jump_url=#{bannerNode.jumpUrl},status=#{bannerNode.status},create_time=#{bannerNode.createTime},sort_num=#{bannerNode.sortNum},grounding_time=#{bannerNode.groundingTime},undercarriage_time=#{bannerNode.undercarriageTime} WHERE id=#{id}  ")
    long updateBannerByBannerId(@Param("id")int id,@Param("bannerNode") BannerNode bannerNode);

    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "status",column = "status")

    })
    @Update("UPDATE banner_node SET status = 0 WHERE id =#{id}")
    void deleteBanner(@Param("id") int id);
}
