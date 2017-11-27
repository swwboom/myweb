/*
 * @Author: LiuLiXue QQ:136890965 
 * @Date: 2017-10-19 09:46:19 
 * @Last Modified by: LiuLiXue
 * @Last Modified time: 2017-10-25 16:20:43
 */
//Slider-Tabs-List页面组件 
<template>
  <div>
    <!-- 轮播组件 -->
    <uws-slider v-if="isShowSlider">
      <uws-slider-item  v-for="(item, index) in sliderList" :key="index">
        <div>
          <a :href="item.jumpUrl">
            <img class="slide-list__pic" :src="item.imgUrl" alt="">
          </a>
        </div>
      </uws-slider-item>
    </uws-slider>
   <!-- 标签组件 -->
    <uws-tabs>
      <uws-tabs-item v-for="(item,index) in tabList" :key="item.id" @click.native="clickTab(item.id, index)">
        <a class="tabs"  v-text="item.name" :class="{active: currentTab === index}"></a>
      </uws-tabs-item>  
    </uws-tabs>
    <!-- 列表组件 -->
    <uws-scroll ref="scroll" :list="articleList" v-on:loadMore="loadMore" v-on:refresh="downRefresh">
      <uws-media-list v-for="(item,index) in articleList" v-bind="item" :key="index" @click.native="goDetail(item.id)"/>
    </uws-scroll>
  </div>
</template>
<script>
  import UwsSlider from '@/components/slider'
  import UwsSliderItem from '@/components/slider/SliderItem'
  import UwsTabs from '@/components/tabs'
  import UwsTabsItem from '@/components/tabs/TabsItem'
  import UwsScroll from '@/components/scroll'
  import UwsMediaList from '@/components/list/mediaList'
  import { getBannerTabs, getArticlesList } from '@/api'
  import status from '@/api/status'
  export default {
    components: {
      UwsSlider,
      UwsSliderItem,
      UwsTabs,
      UwsTabsItem,
      UwsScroll,
      UwsMediaList
    },
    data() {
      return {
        isShowSlider: true,
        status: status.status,
        statusText: status.statusText,
        currentTab: 0,//点击的某一个tab
        moduleId: 0,//模块id
        tabId:0,//点击tab的对应id
        pageNum: 1,//显示的页码数
        pageCount: 10,//每页显示的条数
        sliderList: [],//banner列表
        tabList:[],//tab按钮列表
        articleList: []//文章列表
      }
    },
    created() {
      const _this = this;
      getBannerTabs(_this).then( response => {
        if(this.status == response.status && this.statusText === response.statusText){
          let data = response.data;
          if(0 === data.bannerList.length){
            this.isShowSlider = false
          } else{
            this.sliderList = data.bannerList
          }
          this.tabList = response.data.moduleList
          return this.tabList[0].id
        }
      }).then(
        (id) => {
          this.clickTab(id)
        }
      )
    },
    methods: {
      downRefresh(val){
        this.pageNum = 1;
        this._getList(this.tabId,this.pageNum).then( response => {
          this.articleList = response
        })
      },
      loadMore(val){
        this.pageNum++
        this._getList(this.tabId,this.pageNum).then( response => {
          let list = response
          this.articleList = this.articleList.concat(list)
        })
      },
      //点击tabs
      clickTab(tabId,index=0){
        this.pageNum = 1
        this.tabId = tabId
        this.currentTab = index
        this._getList(this.tabId).then( response => {
          this.articleList = response
        })
      },
      // 进入内容详情
      goDetail(id){
        this.$router.push({path: '/uws-content-detail/'+id})
      },
      //请求接口数据返回一个promise对象供上拉加载和下拉刷新使用
      _getList(tabId,pageNum=1,pageCount=this.pageCount) {
        return getArticlesList(tabId,pageNum,pageCount).then( response => {
                if(this.status == response.status && this.statusText === response.statusText){
                  let list = response.data.articleList
                  if(0 === list.length){
                    this.$refs.scroll.uwsUpdate()
                    return
                  }else{
                    return list
                  }
                }
        })
      }
    }
  }
</script>

<style lang="scss" scoped>
@import '../style/mixin.scss';
  $active-color: #00CF62;
  .tabs{
    display: block;
    width: inherit;
    height: inherit;
    transition: all .3s;
  }
  .active{
    color: #000;
    @include font-dpr(15px);
    &:after{
      content: '';
      display: block;
      width: 100%;
      position: absolute;
      left: 0;
      bottom: 0;
      height: .026667rem /* 2/75 */;
      background-color: $active-color;
    }
  }
</style>


