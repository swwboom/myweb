/*
 * @Author: LiuLiXue QQ:136890965 
 * @Date: 2017-10-19 16:19:07 
 * @Last Modified by: LiuLiXue
 * @Last Modified time: 2017-10-25 14:44:52
 */
// 接口请求文件
import fetch from '@/utils/fetch'

const modListBannerUrl = '/tpl/modListBanner/';//banner列表及分类集合
const listArticlesUrl = '/tpl/listArticles';//获取分类下的文章集合
const questionListUrl = '/tpl/question';//根据问题id获取问题详情
const articleUrl = '/tpl/article';//根据articleId获取文章详情
//获取banner及tabs
export function getBannerTabs(_this) {
    let url = modListBannerUrl + _fn._getModuleId(_this)
     return fetch({
              methods: 'get',
              url
            })
}
//根据tabs的id获取文章列表
export function getArticlesList(tabId,pageNum=1,pageCount=10) {
  let url = `${listArticlesUrl}/${tabId}/${pageNum}/${pageCount}`
  return fetch({
    methods: 'get',
    url
  })
}
//用于问题类页面根据问题id获取问题列表
export function getQuestion(questionId) {
  let url = `${questionListUrl}/${questionId}`
  return fetch({
    methods: 'get',
    url
  })
}
//根据articleId获取问题详情
export function getArticleDetail(articleId) {
  let url = `${articleUrl}/${articleId}`
  return fetch({
    methods: 'get',
    url
  })
}
//不对外暴露私有函数
const _fn = {
  _getModuleId(_this) {
    _this.moduleId = _this.$route.query.moduleId
    return _this.$route.query.moduleId || 1
  }
}