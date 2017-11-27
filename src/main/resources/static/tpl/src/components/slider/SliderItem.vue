/*
 * @Author: LiuLiXue QQ:136890965 
 * @Date: 2017-10-20 16:11:05 
 * @Last Modified by: LiuLiXue
 * @Last Modified time: 2017-10-23 21:48:40
 */
// 轮播子组件在slider标签内,该组件内可以填写任意内容
<template>
  <li class="slide-group__list">
    <slot>
    </slot>
  </li>
</template>

<script>
  export default {
    name: 'UwsSliderItem',
    mounted(){
      this.$nextTick(() => {
        this.$parent.init()
      })
    }
  }
</script>

<style lang="scss" scoped>
  .slide{
    &-group{
      &__list{
        height: inherit;
        display: inline-block;
        box-sizing: border-box;
        overflow: hidden;
        text-align: center;
        div,a,img{
          width: 100%;
          height: inherit;
          display: block;
          overflow: hidden;
        }
      }
    }
  }
</style>


