/*
 * @Author: LiuLiXue QQ:136890965 
 * @Date: 2017-10-18 13:59:11 
 * @Last Modified by: LiuLiXue
 * @Last Modified time: 2017-10-24 15:32:22
 */
/*
 *此组件轮播组件父组件引用index.vue然后在配合sliderItem子组件
 *向外暴露的属性有 height高,interval动画间隔,loop循环播放,autoplay自动播放,indicator小圆点指示器
 */
<template>
  <div class="slide-wrap" ref="slide" :style="{height: sliderHeight}">
    <ul class="slide-group" ref="group">
      <slot>
      </slot>
    </ul>
    <ul class="indicator-wrap" v-if="isShowIndicator">
      <li class="indicator-wrap__item" 
      :class="{active: currentPageIndex === index}"
      v-for="(item,index) in indicators" :key="index"></li>
    </ul>
  </div>
</template>

<script>
  import BScroll from 'better-scroll'
  export default {
    name: 'Slider',
    data() {
      return {
        indicators: [],//指示器数组
        currentPageIndex: 0,//初始化页面
      }
    },
    props: {
      loop: {// 是否循环播放
        type: [ Boolean,String],
        default: true
      },
      indicator: {//是否显示指示器
        type: [ Boolean,String],
        default: true
      },
      autoPlay: {//是否自动播放
        type: [ Boolean,String],
        default: true
      },
      interval: {//播放间隔时间
        type:[ Number,String],
        default: 4000
      },
      speed: {//动画滚动速度
        type: [ Number,String],
        default: 500
      },
      height: {
        type: [ Number,String]
      }
    },
    computed: {
      sliderHeight() {
        return (this.height || 350)/75 + 'rem'
      },
      isLoop() {
        return this.uwsFlag(this.loop)
      },
      isShowIndicator() {
        return this.uwsFlag(this.$parent.indicator)
      },
      isAutoPlay() {
        return this.uwsFlag(this.$parent.autoplay)
      },
      isInterval() {
        return this.uwsFlag(this.$parent.interval)
      },
      isSpeed() {
        return this.uwsFlag(this.$parent.speed)
      },
    },
    methods: {
      init(){
        if(this.slider){return}
        this._setGroupWidth();
        this._initSlide();
        if(this.isAutoPlay){
          this._autoPlay()
        } 
      },
      _initSlide() {
        this.slider= new BScroll(this.$refs.slide,{
          scrollX: true,
          click: true,
          snap: {
            loop: this.isLoop,
            autoPlay: this.autoPlay,
            interval: this.interval,
            speed: this.speed
          }
        });
        window.addEventListener('resize',() => {
          if(!this.slider || !this.slider.enabled){
            return
          }
          clearTimeout(this.resizeTimer)
          this.resizeTimer = setTimeout(()=>{
            if(this.slider.isInTransition){
              this._scrollEnd()
            }else{
              if(this.autoPlay){
                this._autoPlay()
              }
            }
            this._setGroupWidth(true)
            this.slider.refresh()
          },60)
        })
        this.slider.on('beforeScrollStart',() => {
          if(this.autoPlay){
            clearTimeout(this.timer)
          }
        })
        this.slider.on('scrollEnd',this._scrollEnd)
        this.slider.on('touchEnd', ()=>{
          if(this.autoPlay){
            this._autoPlay()
          }
        })
      },
      beforeDestroy() {
        this.slider.disable()
        clearTimeout(this.timer)
      },
      _setGroupWidth(isResize) {
        let groupWidth = 0;
        let wrapWidth = this.$refs.slide.clientWidth
        // let wrapWidth = document.documentElement.clientWidth - this.$refs.slide.getBoundingClientRect().left
        let gChildren = this.$refs.group.children
        let cLen = gChildren.length
        this.children = this.$refs.group.children
        this.$refs.slide.style.width = wrapWidth + 'px'
        if(1 === cLen){
          groupWidth = wrapWidth + 'px'
          gChildren[0].style.width= wrapWidth + 'px'
          return
        }
        if(this.isShowIndicator && cLen > 1){
          this._getIndicators();
        }
        for(let i = 0, len = gChildren.length; i<len; i++){
          groupWidth+=wrapWidth;//group的宽度
          gChildren[i].style.display = 'inline-block';
          gChildren[i].style.height= 'inherit';
          gChildren[i].style.width = wrapWidth + 'px';//设置每个slider下list的宽度
        }
        if( this.isLoop && !isResize ){
          groupWidth+= 2 * wrapWidth;
        }
        this.$refs.group.style.width = groupWidth + 'px';
        // if(this.isShowIndicator && (0 !== gChildren.length)){
        //   this._getIndicators();
        // }
      },
      _scrollEnd() {
        let pageIndex = this.slider.currentPage.pageX
          if(this.isLoop){
            pageIndex -= 1
          }
          this.currentPageIndex = pageIndex
          if(this.autoPlay){
            this._autoPlay()
          } 
      },
      _autoPlay() {
        let pageIndex  = this.slider.currentPage.pageX;
        clearInterval(this.timer);
        this.timer = setTimeout(() => {
          this.slider.goToPage(pageIndex+1,0,this.speed);
        },this.interval)
      },
      _getIndicators() {
        this.indicators = this.$refs.group.children.length;
      }
    },
  }
</script>

<style lang="scss" scoped>
  $indicator-bg-color: rgba(255,255,255,.7);
  $indicator-active-bg-color: rgba(255,255,255,1);
  .slide{
    &-wrap{
      min-height: 1px;
      overflow: hidden;
      position: relative;
    }
    &-group{
      height: inherit;
      overflow: hidden;
      white-space: nowrap;
      a,img{
        width: 100%;
        height: inherit;
        display: block;
        overflow: hidden;
      }
    }
  }
  .indicator-wrap{
    position: absolute;
    left: 0;
    right: 0;
    bottom: .16rem /* 12/75 */;
    transform: translateZ(1px);
    text-align: center;
    font-size: 0;
    &__item{
      display: inline-block;
      margin: 0 .053333rem /* 4/75 */;
      width: .133333rem /* 10/75 */;
      height: .133333rem /* 10/75 */;
      border-radius: 50%;
      background-color: $indicator-bg-color;
      transition: width 100;
      &.active{
        width: .266667rem /* 20/75 */;
        border-radius: .066667rem /* 5/75 */;
        background-color: $indicator-active-bg-color
      }
    }
  }
  .img-wrap{
    img{
      width: 100%;
    }
  }
</style>


