/*
 * @Author: LiuLiXue QQ:136890965 
 * @Date: 2017-10-19 14:33:35 
 * @Last Modified by: LiuLiXue
 * @Last Modified time: 2017-10-26 16:22:09
 */
 /**
  *该组件向外发射loadMore加载更多事件并附带一个字符串参数loading,供下拉加载用,
  *向外发射refresh下拉刷新事件附带refresh字符串参数,供下拉加载更新用
  *参数用于页面逻辑请求数据后台区分是下拉刷新还是上拉加载
  *如果请求的数据接口没有新的数据必须在父组件手动调用uwsUpdate方法
  */
//tab下的内容列表容器组件动态获取容器高度超出高度产生滚动效果
<template>
  <div class="wrap" ref="wrap">
    <!-- 列表容器 -->
    <div class="scroll-content" ref="container">
      <ul class="list-wrap">
        <li>
          <slot>
            <!-- 插入显示的列表列表样式需自己写 -->
          </slot> 
        </li>
      </ul> 
        <!-- 上拉加载更多 -->
      <div class="pullup-wrap" v-if="pullUpLoad">
        <div class="before-trigger" v-if="!isPullUpLoad">
          <span>{{pullUpTxt}}</span>
        </div>
        <div class="after-trigger" v-else>
          <up-loading/>
        </div>
      </div><!--upend-->
    </div>
    <!-- 下拉刷新 -->
    <div class="pulldown-wrap" ref="pulldown" :style="pullDownStyle" v-if="pullDownRefresh">
      <!-- start -->
      <div class="before-trigger" v-if="beforePullDown">
        <down-refresh :y="refreshY"/>
      </div><!--end-->
      <!-- endPull -->
      <div class="after-trigger" v-else>
        <div class="loading" v-if="pulling">
          <up-loading/>
        </div>
        <div class="refresh-info" v-else>
          <span>{{refreshTxt}}</span>
        </div>
      </div><!--loadend-->
    </div>
  </div>
</template>

<script>
  import BScroll from 'better-scroll'
  import DownRefresh from './refresh' 
  import UpLoading from './Loading'
  export default{
    name: 'Scroll',
    components: {
      DownRefresh,
      UpLoading
    },
    props: {
      probeType: {
        type: Number,
        default: 1
      },
      scrollY: {
        type: Boolean,
        default: true
      },
      /**刷新时长 */
      refreshDelay: {
        type: Number,
        default: 20
      },
      /**是否开始点击事件 */
      click: {
        type: Boolean,
        default: true
      },
      /** 纵轴方向初始化位置，*/
      startY: {
        type: Number,
        default: 0
      },
      /**是否显示滚动条
       * 这个配置可以开启滚动条，默认为 false。
       * 当设置为 true 或者是一个 Object 的时候，都会开启滚动条，
       * 默认是会 fade 的
       */
      scrollbar: {
        type: Boolean,
        default: false
      },
      /**这个配置用于做上拉加载功能，默认为 false。
       * 当设置为 true 或者是一个 Object 的时候，
       * 可以开启上拉加载*
       **/
      pullUpLoad: {
        type: null,
        default: () => {
          return {
            threshold: 100,
            loadInfo: {
              more: '上拉加载更多...',
              noMore: '没有更多数据了'
            }
          }
        }
      },
      /***派发顶部下拉事件用于下拉刷新
       * 这个配置用于做下拉刷新功能，默认为 false。
       * 当设置为 true 或者是一个 Object 的时候，可以开启下拉刷新
       * 可以配置顶部下拉的距离（threshold） 来决定刷新时机以及回弹停留的距离（stop）
       * */
      pullDownRefresh: {
        type: null,
        default:  () => {
          return {
            threshold: 70,
            refreshInfo: {
              more: '更新完成'
            }
          }
        }
      },
      /**派发滚动开始事件**/
      beforeScroll: {
        type: Boolean,
        default: false
      },
      /**派发滚动事件 */
      listenScroll: {
        type: Boolean,
        default: false
      },
      list: {//传入列表数据
        type: Array,
        default: () => {
          return []
        }
      }
    },
    data() {
      return {
        beforePullDown: true,
        isPullingDown: false,
        isRebounding: false,
        isPullUpLoad: false,
        pulling: false,
        pullUpDirty: true,
        refreshY: 0,
        pullDownStyle: '',
        pullDowninitTop: -50
      }
    },
    computed: {
      pullUpTxt() {
        let moreTxt = this.pullUpLoad && this.pullUpLoad.loadInfo && this.pullUpLoad.loadInfo.more
        let noMoreTxt = this.pullUpLoad && this.pullUpLoad.loadInfo && this.pullUpLoad.loadInfo.noMore
        return this.pullUpDirty ? moreTxt : noMoreTxt
      },
      refreshTxt() {
        return this.pullDownRefresh && this.pullDownRefresh.refreshInfo.more
      }
    },
    mounted() {
      this.$nextTick(() => {
        new Promise((resolve, reject) => {
          let succ = this._getWrapHeight()
          if('succ' === succ){
            resolve()
          }else{
            reject()
          }
        }).then(() => {
          this.initScroll()
        })
      })
    },
    methods: {
      initScroll() {
         if(!this.$refs.wrap){//如果不存在最外层父包裹元素则返回
           return
         }
         /**
          * 如果存在内容容器并且开启了上拉或者下拉功能设置容器最小高度
          */
        //  if(this.$refs.container && (this.pullDownRefresh || this.pullUpLoad)){
        //    this.$refs.container.style.minHeight = this.wrapHeight + 1+ 'px'
        //  }
         /**
          * 初始化滚动组件
          */
         let options = {
           probeType: this.probeType,
           click: this.click,
           scrollY: this.scrollY,
           scrollbar: this.scrollbar,
           pullDownRefresh: this.pullDownRefresh,
           pullUpLoad: this.pullUpLoad,
           startY: this.startY
         }
         this.scroll = new BScroll(this.$refs.wrap, options)
         /**
         * 开启滚动开始事件
         */
        if(this.beforeScroll){
          this.scroll.on('beforeScrollStart',() => {
            this.$emit('beforeScroll')
          })
        }
        /**
         * 如果开启了滚动监听则进行下列操作向父元素
         * 发送一个scroll事件
         * pos参数为滚动的实时坐标
         */
        if(this.listenScroll){
          this.scroll.on('scroll',(pos) => {
            this.$emit('scroll',pos)
          })
        }
        /**
         * 开启下拉刷新
         */
        if(this.pullDownRefresh){
          this._initPullDown()
        }
        /**
         * 开启上拉加载
         */
        if(this.pullUpLoad){
          this._initPullUp()
        }
      },
      _getWrapHeight() {
        this.wrapHeight =document.documentElement.clientHeight - this.$refs.wrap.getBoundingClientRect().top;
        this.$refs.wrap.style.height = this.wrapHeight + 'px';
        if(this.$refs.container && (this.pullDownRefresh || this.pullUpLoad)){
           this.$refs.container.style.minHeight = this.wrapHeight + 1+ 'px'
         }
        return 'succ'
      },
      // 在一次下拉刷新的动作后，这个时机一般用来去后端请求数据。
      _initPullDown() {
        this.scroll.on('pullingDown', (pos) => {
          this.beforePullDown = false
          this.isPullingDown = true
          this.pulling = true
          this.$emit('refresh','refresh')
        })
        this.scroll.on('scroll', (pos)=>{
          if(this.beforePullDown){
            this.refreshY = Math.max(0, pos.y + this.pullDowninitTop)
            this.pullDownStyle = `top:${Math.min(pos.y + this.pullDowninitTop,10)}px`
          }else{
            this.refreshY = 0;
          }
          if(this.isRebounding){
            this.pullDownStyle = `top:${10 - (this.pullDownRefresh.stop-pos.y)}px`
          }
        })
      },
      //触发时机：在一次上拉加载的动作后，这个时机一般用来去后端请求数据。
      _initPullUp() {
        this.scroll.on('pullingUp', () => {
          this.isPullUpLoad = true
          this.$emit('loadMore','loading')
        })
      },
      _reboundPullDown() {
        let {stopTime = 600 } = this.pullDownRefresh
        return new Promise((resolve,reject) => {
          setTimeout(() => {
            this.isRebounding = true
            this.scroll.finishPullDown()
            this.isPullingDown = false,
            resolve()
          },stopTime)
        })
      },
      _afterPullDown() {
        setTimeout( ()=>{
          this.pullDownStyle = `top:${this.pullDowninitTop}px`
          this.beforePullDown = true
          this.isRebounding = false
          this.refresh()
        },this.scroll.options.bounceTime)
      },
      /**启用scroll */
      enable(){
        this.scroll && this.scroll.enable()
      },
      /**禁用scroll */
      disable(){
        this.scroll && this.scroll.disable()
      },
      /**数据改变后属性DOM重新计算保证滚动效果 */
      refresh() {
        this.scroll && this.scroll.refresh()
      },
      uwsUpdate(dirty) {
        if(this.pullDownRefresh && this.isPullingDown){
          this.pulling = false
          this._reboundPullDown().then(() => {
            this._afterPullDown()
          })
        }else if(this.pullUpLoad && this.isPullUpLoad){
          this.isPullUpLoad = false
          this.scroll.finishPullUp()
          this.pullUpDirty = dirty
          this.refresh()
        }else {
          this.refresh()
        }
      }
    },
    watch: {
      list(newVal,oldVal) {
        setTimeout(() => {
          this._getWrapHeight()
          this.uwsUpdate(true)
        },this.refreshDelay)
        
      }
    },
  }
</script>

<style lang="scss" scoped>
  @import '../../style/mixin';
  $border-color: rgba(163,163,163,1);
  .wrap{
    position: relative;
    overflow: hidden;
    margin-top: .04rem /* 3/75 */;
  }
  .scroll-content{
    width: 100%;
    box-sizing: border-box;
    // padding: 0 .346667rem /* 26/75 */;
    .list-wrap{
      position: relative;
      z-index: 100;
    }
  }
  .pulldown-wrap{
    position: absolute;
    width: 100%;
    left: 0;
    // top:-.666667rem /* 50/75 */;
    display: flex;
    justify-content: center;
    align-items: center;
    transition: all;
    .after-trigger{
      margin-top: 10px;
    }
    .refresh-info{
      color: rgba(50,135,193,.5);
      @include font-dpr(12px);
    }
  }
  .pullup-wrap{
    @include font-dpr(12px);
    color: rgba(50,135,193,.6);
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: .213333rem /* 16/75 */ 0;
  }
</style>

