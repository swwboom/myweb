<template>
  <div id="app" class="app">
    <router-view/>
  </div>
</template>

<script>
  import './static/js/libs/flexible.js';
  import './static/iconfont/iconfont.js'
export default {
  name: 'app'
}
</script>

<style lang='scss'>
// 全局通用样式
  @import 'static/scss/reset';
  @import './style/mixin';
  @import './style/_variables';
  $a-color: #333;
  $a-font-size: 13px;
  @for $i from 1 through 6 {
    h#{$i} {
      padding: .066667rem /* 5/75 */ 0;
    }
  }
  html{
    width: 100%;
    height: 100%;
    height: 100vh;
  }
  body{
    height: inherit;
    background-color: $body-bg-color;
    >.app{
      width: inherit;
      height: inherit;
    }
  }
  a{
    color: $a-color;
    @include font-dpr($a-font-size);
  }
  // 字体图标
  .icon {
    width: 1em; height: 1em;
    vertical-align: -0.15em;
    fill: currentColor;
    overflow: hidden;
  }
</style>
