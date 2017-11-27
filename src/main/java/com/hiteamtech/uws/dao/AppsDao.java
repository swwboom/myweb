package com.hiteamtech.uws.dao;

import com.hiteamtech.uws.resultmapping.cms.AppInfo;
import org.apache.ibatis.annotations.*;
/**
 * Created by William shaw on 2017/9/29.
 * 根据数据库查询，根据用户ID映射出APP name
 */

import java.util.List;
import java.util.Map;

@Mapper
public interface AppsDao {
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "appName", column = "app_name"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "appIntroduction",column = "app_Introduction"),
            @Result(property = "userId",column = "user_id")
    })
    @Select("SELECT id,app_name,create_time,app_Introduction,user_id from app_info where status = 1 and user_id =#{userId} order by id limit #{page},#{pageCount}")
    List<Map<String,Object>> findAppInfoByUserId(@Param("userId")  int userId, @Param("page") int page1, @Param("pageCount") int pageCount);

    @Select({"SELECT id,app_name as appName,app_introduction as appIntroduction,create_time as createTime,user_id as userId from app_info where id = #{id} and status = 1"})
    Map<String,Object> findAppByUserId(@Param("id") int id);

    @Select("SELECT count(*) from app_info where status=1 and user_id = #{userId}")
    long countAppInfoByUserId(@Param("userId") int userId);

    @Select("SELECT id,app_name as appName from app_info where status=1 and user_id = #{userId}")
    List<Map<String,Object>> findAllAppsByUserId(@Param("userId") int userId);


    @Update("UPDATE app_info SET status = 0 WHERE id =#{id}")
    void deleteApp(@Param("id") int id);
    /**
     * Update by di on 2017/9/29.
     * 增加产品
     */
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "appName", column = "app_name"),
            @Result(property = "userId" , column = "user_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "appIntroduction",column = "app_introduction"),
            @Result(property = "status",column = "status")
    })
    @Insert("INSERT INTO app_info (app_name,user_id,create_time,app_introduction,status) VALUES (#{appInfo.appName},#{appInfo.userId},#{appInfo.createTime},#{appInfo.appIntroduction},#{appInfo.status})")
    @Options(useGeneratedKeys = true, keyProperty = "app_info.id")
    int addAppInfo(@Param("appInfo") AppInfo appInfo);
}
