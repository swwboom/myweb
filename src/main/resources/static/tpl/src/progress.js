/*
 * @Author: LiuLiXue QQ:136890965 
 * @Date: 2017-10-19 11:28:05 
 * @Last Modified by: LiuLiXue
 * @Last Modified time: 2017-10-25 14:08:14
 */
import Router from './router'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

Router.beforeEach((to, from, next) => {
  NProgress.start()
  setTimeout(()=>{next()},500)
  
})

Router.afterEach((to, from) => {
  NProgress.done(from)
})