/*
 * @Author: LiuLiXue QQ:136890965 
 * @Date: 2017-10-18 20:43:40 
 * @Last Modified by: LiuLiXue
 * @Last Modified time: 2017-10-23 17:57:05
 */
//横向的tabs组件
<template>
  <div class="wrap" ref="wrap">
      <ul class="common" :class="[ flex ? isFlex : isGroup ]" ref="group">
        <slot></slot>
      </ul>
  </div>
</template>

<script>
  import BScroll from 'better-scroll';
  export default {
    name: 'Tabs',
    data() {
      return {
        tabsLen: 4,//默认tabs显示数目
        flex: false,//是否使用flex布局
        isFlex: 'isFlex',//动态添加class，tabs<=4使用flex布局>4产生滚动
        isGroup: 'isGroup',
        path: ''
      }
    },
    beforeDestroy() {
      this.tabs.disable()
    },
    methods: {
      initTab() {
        if(this.tabs){
          return
        }
        this._getListWidth()
        this.tabs = new BScroll(this.$refs.wrap,{
          scrollX: true,
          click: true
        })
        window.addEventListener('resize',() => {
          if(!this.tabs || !this.tabs.enabled){
            return
          }
          this._getGroupWidth()
          this.tabs.refresh()
        })
      },
      _getGroupWidth() {
        let groupWidth = 0;
        for(let i=0, len = this.children.length;i<len;i++){
          groupWidth+=this.children[i].clientWidth
        }
        this.$refs.group.style.width = groupWidth + 'px'
      },
      _getListWidth() {
        this.children = this.$refs.group.children
        let childrenLen = this.children.length
        let wrapWidth = this.$refs.wrap.clientWidth
        if(childrenLen<=this.tabsLen){
          this.flex = true
          for(let i=0; i<childrenLen; i++){
            this.children[i].style.flex = 1;
          }
          return
        }else{
          this.flex = false
          for(let i=0; i<childrenLen; i++){
            this.children[i].style.width = wrapWidth / 4 + 'px';
          }
          this._getGroupWidth()
          return
        }
      }
    }
  }
</script>

<style lang="scss" scoped>
  $wrap-height: 1.28rem;
  $tabs-bg-color: rgba(255,255,255,1);
 .wrap{
   width: 100%;
   height: $wrap-height;
   line-height: $wrap-height;
   margin-bottom: .026667rem /* 2/75 */;
   overflow: hidden;
   text-align: center;
   box-shadow: 0 2px 4px #999;
   .isFlex{
     width: inherit;
     display: flex;
     justify-content: space-around;
     align-items: center;
     .list{
       flex: 1;
     }
   }
   .isGroup{
     white-space: nowrap;  
     .list{
       position: relative;
       display: inline-block;
       box-sizing: border-box;
     }
   }
   .common{
     height: inherit;
     box-sizing: border-box;
     background-color: $tabs-bg-color;
     .list{
       height: inherit;
     }
     a{
        display: block;
        width: inherit;
        height: inherit;
        &:after{
        content: '';
        display: block;
        position: absolute;
        bottom: .013333rem /* 1/75 */;
        width: 100%;
        height: .026667rem /* 2/75 */;
        border-bottom: 2px solid transparent;
        }
       }
   }
 }
</style>


