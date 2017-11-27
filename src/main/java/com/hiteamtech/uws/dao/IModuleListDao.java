package com.hiteamtech.uws.dao;

import com.hiteamtech.uws.resultmapping.tpl.ModuleList;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Map;

/**
 * Created on 2017/9/21.
 */
@Mapper
public interface IModuleListDao {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "sortName", column = "sortName"),
            @Result(property = "status",column = "status"),
            @Result(property = "moduleId",column = "module_id"),
            @Result(property = "moduleListId",column = "module_list_id")
    })

    @Select("SELECT id, name FROM module_list where module_id = #{moduleId}")
    List<Map<String,Object>> findListByModulesId(@Param("moduleId") int moduleId);
    /**
     * 查询指定模块的分类列表，默认排序sortNum倒序
     */
    @Select("select id,name from module_list where module_id = #{moduleId} and status = 1 order by sort_num ")
    List<Map<String,Object>> findModuleArticleListByModuleId(@Param("moduleId") int moduleId);
    /**
     * 给模块增加分类
     */
    @Insert("insert into module_list (module_id,name,sort_num,status) values (#{moduleList.moduleId},#{moduleList.name},#{moduleList.sortNum},#{moduleList.status})")
    @Options(useGeneratedKeys = true,keyProperty = "moduleList.id")
    int addModuleList(@Param("moduleList") ModuleList moduleList);
    /**
     * 删除模块分类
     */
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "status",column = "status"),
    })
    @Update("UPDATE module_list SET status = 0 WHERE id =#{id}")
    void deleteModuleList(@Param("id") int id);
}
