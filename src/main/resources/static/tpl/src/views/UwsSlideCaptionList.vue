/*
 * @Author: LiuLiXue QQ:136890965 
 * @Date: 2017-10-24 11:56:09 
 * @Last Modified by: LiuLiXue
 * @Last Modified time: 2017-10-25 15:38:42
 */
/**问题类模板
  列表页面
 */

 <template>
   <div>
     <!-- 轮播组件 -->
     <div class="slider">
       <uws-slider v-if="isShowSlider" :height="330">
          <uws-slider-item  v-for="(item, index) in sliderList" :key="index">
            <div>
              <a :href="item.jumpUrl">
                <img class="slide-list__pic" :src="item.imgUrl" alt="">
              </a>
            </div>
          </uws-slider-item>
        </uws-slider>
     </div>
    <!-- 标题组件 -->
    <uws-caption :iconClass="icon">
      <span v-text="caption"></span>
    </uws-caption>
    <!-- 滚动列表组件 -->
    <uws-scroll ref="scroll" :list="articleList" v-on:loadMore="loadMore" v-on:refresh="downRefresh">
      <uws-scroll-item v-for="item in articleList" :key="item.id">
        <router-link class="href" :to="{path: '/uws-answer/'+item.id}" v-text="item.title"></router-link>
      </uws-scroll-item>
    </uws-scroll>
   </div>
 </template>

 <script>
  import UwsSlider from '@/components/slider'
  import UwsSliderItem from '@/components/slider/SliderItem'
  import UwsCaption from '@/components/caption/Caption.vue'
  import UwsScroll from '@/components/scroll'
  import UwsScrollItem from '@/components/list/NormalList'
  import { getBannerTabs, getArticlesList } from '@/api'
  import status from '@/api/status'
  export default {
    name: 'UwsSlideCaptionList',
    components: {
      UwsSlider,
      UwsSliderItem,
      UwsCaption,
      UwsScroll,
      UwsScrollItem
    },
    data() {
      return {
        isShowSlider: true,
        status: status.status,
        statusText: status.statusText,
        moduleId: 0,//模块id
        tabId: 0,//标题id用于列表请求
        pageNum: 1,//显示的页码数
        pageCount: 10,//每页显示的条数
        icon: 'wenhao',
        isShowSlider: true,
        caption: '',//标题
        sliderList: [],
        tabList: [],
        articleList: []
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
          this.caption = data.moduleList[0].name
          this.tabId = data.moduleList[0].id
          return this.tabId
        }
      }).then( id => {
          this.initData(id)
        }
      )
    },
    methods: {
      initData(tabId) {
        this._getList(tabId).then( response => {
          this.articleList = response
        })
      },
      downRefresh(val){
        this.pageNum = 1;
        this._getList(this.tabId,this.pageNum).then( response => {
          if(!response){
            this.articleList = []
            return
          }
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
  .href{
    width: inherit;
    height: inherit;
  }
 </style>