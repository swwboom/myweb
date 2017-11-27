package com.hiteamtech.uws.service.cms;
import com.hiteamtech.uws.resultmapping.tpl.Modules;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IModuleService {
    /**
     * Updata by di on 2017/9/29.
     * 根据模块ID删除模块
     */
    public HttpStatus deleteModule(int id);
    /**
     * 添加模块
     */
    ResponseEntity<Map> addModules(Modules modules);
    /**
     * Updata by di on 2017/9/29.
     * 查询模块详情
     */
    ResponseEntity<Map> findModuleById(int id);
    /**
     * Updata by di on 2017/9/29.
     * 查询指定app模块列表
     */
    ResponseEntity<Map> findModuleByAppId(int userId, int page,int pageCount);

    /**
     * 根据模块id分页查询banner列表
     * @param moduleId
     * @param page
     * @param pageCount
     * @return
     */
    Map findBannersByModuleId(int moduleId, int page, int pageCount);

}
