/*
 * @Author: LiuLiXue QQ:136890965 
 * @Date: 2017-10-25 09:52:04 
 * @Last Modified by: LiuLiXue
 * @Last Modified time: 2017-10-26 15:37:23
 */
 /**
  *需要手动传入data属性为获得的数据
  */
//这只是一个简单的滚动容器不带上拉和下拉功能

<template>
  <div class="wrap" ref="scroll">
    <div class="container">
      <slot>
      </slot>
    </div>
  </div>
</template>

<script>
  import BScroll from 'better-scroll'
  export default {
    name: 'UwsNormalScroll',
    props: {
      probeType: {
        type: Number,
        default: 1
      },
      click: {
        type: Boolean,
        default: true
      },
      scrollY: {
        type: Boolean,
        default: true
      },
      scrollbar: {
        type: Boolean,
        default: false
      },
      refreshDelay: {
        type: Number,
        default: 20
      },
      data: {
        type: null
      }
    },
    mounted() {
      this.$nextTick( () => {
        this.initScroll()
      })
    },
    watch: {
      data() {
        setTimeout( () => {
          this.refresh()
        },this.refreshDelay)
      }
    },
    methods: {
      initScroll() {
        this._getHeight()
        let options = {
          probeType: this.probeType,
          click: this.click,
          scrollY: this.scrollY,
          scrollbar: this.scrollbar
        }
        if(this.scroll && !this.$refs.scroll){
          return
        }
        this.scroll = new BScroll(this.$refs.scroll,options)
      },
      disable() {//禁用
        this.scroll && this.scroll.disable()
      },
      enable() {//启用
        this.scroll && this.scroll.enable()
      },
      refresh() {//数据更新以后通过此方法重新刷新DOM结构
        this._getHeight()
        this.scroll && this.scroll.refresh()
      },
      destroy() {},
      _getHeight() {
        this.$refs.scroll.style.height = document.documentElement.clientHeight - this.$refs.scroll.getBoundingClientRect().top + 'px'
      }
    }
  }
</script>

<style lang="scss" scoped>
  .wrap{
    overflow: hidden;
    .container{
      width: inherit;
    }
  }
</style>
