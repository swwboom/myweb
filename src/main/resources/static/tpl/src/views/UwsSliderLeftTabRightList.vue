/*
 * @Author: LiuLiXue QQ:136890965 
 * @Date: 2017-10-26 16:02:49 
 * @Last Modified by: LiuLiXue
 * @Last Modified time: 2017-10-26 16:34:15
 */
//页面级组件
/**上面轮播图下层左侧tab右侧list */
<template>
   <div class="wrap">
     <div class="ceil-wrap">
       <!-- 轮播组件 -->
        <uws-slider v-if="isShowSlider">
          <uws-slider-item v-for="(item, index) in sliderList" :key="index">
            <div>
              <a :href="item.jumpUrl">
                <img class="slide-list__pic" :src="item.imgUrl" alt="">
              </a>
            </div>
          </uws-slider-item>
        </uws-slider>
     </div>
     <div class="floor-wrap">
       <!-- left-wrap -->
      <div class="left-wrap">
        <uws-normal-scroll :data="tabList">
          <div class="class-wrap">
            <div class="item" :class="{active: currentTab === index}" v-for="(item,index) in tabList" :key="item.id" v-text="item.name" @click="clickTab(item.id,index)"></div>
          </div>
        </uws-normal-scroll>
      </div><!--left-wrap-end-->
      <!-- right-wrap -->
      <div class="right-wrap">
      
        <!-- 滚动列表组件 -->
        <uws-scroll ref="scroll" :list="articleList" v-on:loadMore="loadMore" v-on:refresh="downRefresh">
          <uws-media-list v-for="(item,index) in articleList" v-bind="item" :key="index" @click.native="goDetail(item.id)"/>
        </uws-scroll>
      </div>
      </div><!--right-wrap-end-->
   </div>
 </template>
 
 <script>
  import UwsSlider from '@/components/slider'
  import UwsSliderItem from '@/components/slider/SliderItem'
  import UwsScroll from '@/components/scroll'
  import UwsNormalScroll from '@/components/scroll/NormalScroll'
  import UwsMediaList from '@/components/list/mediaList'
  import { getBannerTabs, getArticlesList } from '@/api'
  import status from '@/api/status'
  export default {
    name: 'UwsSlideLeftTabRight',
    components: {
      UwsNormalScroll,
      UwsSlider,
      UwsSliderItem,
      UwsScroll,
      UwsMediaList
    },
    data() {
      return {
        status: status.status,
        statusText: status.statusText,
        isShowSlider: true,
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
      const _this = this
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
  @import '../style/mixin';
  @import '../style/_variables';
  $active-color: #1ccb62;
  %bg-color{
    background-color: $common-bg-color;
  }
  // 整体布局
  .wrap{
    width: 100%;
    height: inherit;
  }
  .floor-wrap{
    display: flex;
  }
  // 左侧布局
  .left-wrap{
    flex:0 0 $left-width;
    border-right: .013333rem /* 1/75 */ solid $border-color;
    @extend %bg-color;
    .class-wrap{
      box-sizing: border-box;
      padding: .48rem /* 36/75 */ 0;
      display: flex;
      flex-direction: column;
      justify-content: flex-start;
      align-items: center;
      .item{
        height: .466667rem /* 35/75 */;
        position: relative;
        margin-bottom: .533333rem /* 40/75 */;
        @include font-dpr(14px);
        color: #888686;
      }
      .active{
        color: $active-color;
        &:after{
          content: '';
          width: 100%;
          height: .026667rem /* 2/75 */;
          position: absolute;
          bottom: -.053333rem /* 4/75 */;
          left: 0;
          background-color: $active-color;
        }
      }
    }
  }
  // 右侧布局
  .right-wrap{
    flex: 1;
    @extend %bg-color;
  }
 </style>
 

</style>
