import Vue from 'vue'
import Router from 'vue-router'
const _import_ = require('./_import_' + process.env.NODE_ENV)
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: _import_('Hello')
    },
    {
      path: '/uws-slider-tabs-list',
      name: 'SliderTabsList',
      component: _import_('UwsSlideTabList')
    },
    {
      path: '/uws-content-detail/:id',
      name: 'UwsContentDetail',
      component: _import_('UwsContentDetail')
    },
    {
      path: '/uws-leftTab-rightslideList',
      name: 'UwsLeftTabRightSlideList',
      component: _import_('UwsLeftTabRightSlideList')
    },
    {
      path: '/uws-slide-leftTab-rightList',
      name: 'UwsSlideLeftTabRightList',
      component: _import_('UwsSliderLeftTabRightList')
    },
    {
      path: '/question-slider-caption-list',
      name: 'SliderCaptionList',
      component: _import_('UwsSlideCaptionList')
    },
    {
      path: '/uws-answer/:id',
      name: 'UwsAnswer',
      component: _import_('UwsAnswer')
    }
  ]
})
