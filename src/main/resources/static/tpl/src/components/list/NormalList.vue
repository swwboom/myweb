/*
 * @Author: LiuLiXue QQ:136890965 
 * @Date: 2017-10-24 15:54:28 
 * @Last Modified by: LiuLiXue
 * @Last Modified time: 2017-10-24 16:23:35
 */
// 一般列表
<template>
  <div class="item-wrap">
    <slot>
    </slot>
  </div>
</template>

<script>
  export default {
    name: 'NormalList'
  }
</script>

<style lang="scss" scoped>
  @import '../../style/mixin';
  @import '../../style/_variables';
  .item-wrap{
    @include font-dpr(15px);
    @include line-height-dpr(18px);
    width: 100%;
    box-sizing: border-box;
    padding: $caption-padding-lr;
    color: rgba(81,81,81,1);
    background-color: $common-bg-color;
  }
</style>


