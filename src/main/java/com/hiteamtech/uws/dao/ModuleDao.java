package com.hiteamtech.uws.dao;
import com.hiteamtech.uws.resultmapping.tpl.Modules;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Mapper
public interface ModuleDao {
    /**
     * Update by on 2017/9/29.
     * 增加模块
     */
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "appId" , column = "app_id"),
            @Result(property = "type", column = "type"),
            @Result(property = "describe",column = "modules_describe"),
            @Result(property = "status",column = "status")

    })
    @Insert("INSERT INTO modules (name,app_id,type,modules_describe,status) VALUES (#{modules.name},#{modules.appId},#{modules.type},#{modules.describe},#{modules.status})")
    @Options(useGeneratedKeys = true, keyProperty = "modules.id")
    int addModules(@Param("modules") Modules modules);

    /**
     * Update by on 2017/9/29.
     * 删除模块
     */
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "status",column = "status")

    })
    @Update("UPDATE modules SET status = 0 WHERE id =#{id}")
    void deleteModule(@Param("id") int id);
    /**
     * Update by  on 2017/9/29.
     * 根据模块id查询模块详情
     */
    @Select({"SELECT * from modules  where id = #{id} and status = 1"})
    List<Map<String, Object>> findModuleById(@Param("id") int id);
    /**
     * Update by  on 2017/9/29.
     * 查询app模块列表
     */
    @Select({"SELECT id,name,modules_describe as modulesDescribe,type  from modules  where app_id = #{appId} and status = 1  order by id limit #{page},#{pageCount}"})
    List<Map<String,Object>> findModuleByAppId(@Param("appId") int appId , @Param("page") int page1, @Param("pageCount") int pageCount);

    @Select("SELECT count(*) from modules where app_id=#{appId} and status=1")
    long findModuleCount(@Param("appId") int appId);

    @Select("SELECT bn.id,bn.img_url imgUrl,bn.jump_url jumpUrl,bn.banner_id bannerId,bn.sort_num sortNum\n" +
            "FROM module_banner mb\n" +
            "LEFT JOIN banner_node bn ON mb.banner_id=bn.banner_id\n" +
            "WHERE mb.modules_id=#{moduleId} AND bn.status=1 limit #{page},#{pageCount}")
    List<Map> findBannersByModuleId(@Param("moduleId") int moduleId,@Param("page")int page, @Param("pageCount")int pageCount);

    @Select("SELECT count(bn.id)\n" +
            "FROM module_banner mb\n" +
            "LEFT JOIN banner_node bn ON mb.banner_id=bn.banner_id\n" +
            "WHERE mb.modules_id=#{moduleId} AND bn.status=1")
    int findBannerNumByModuleId(@Param("moduleId") int moduleId);
}
