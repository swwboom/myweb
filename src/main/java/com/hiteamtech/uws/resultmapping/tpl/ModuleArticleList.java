package com.hiteamtech.uws.resultmapping.tpl;
/**
 * Created by 刘旭 on 2017/9/22.
 * 分类下的文章列表集合
 */

public class ModuleArticleList {
    private  int id;
    private  int module_list_id;
    private  int module_list_article;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModule_list_id() {
        return module_list_id;
    }

    public void setModule_list_id(int module_list_id) {
        this.module_list_id = module_list_id;
    }

    public int getModule_list_article() {
        return module_list_article;
    }

    public void setModule_list_article(int module_list_article) {
        this.module_list_article = module_list_article;
    }

    @Override
    public String toString() {
        return "ModuleArticleList{" +
                "id=" + id +
                ", module_list_id=" + module_list_id +
                ", module_list_article=" + module_list_article +
                '}';
    }
}
