/*
 * @Author: LiuLiXue QQ:136890965 
 * @Date: 2017-10-24 14:24:01 
 * @Last Modified by: LiuLiXue
 * @Last Modified time: 2017-10-24 20:51:27
 */
// 字体图标组件基于symbol引用
<template>
  <svg class="icon" aria-hidden="true">
    <use :xlink:href="icon"></use>
  </svg>
</template>

<script>
export default {
  name: 'IconFont',
  props: {
    iconName: {
      type: String
    }
  },
  computed: {
    icon() {
      let name = this.$parent.iconName || this.iconName
      return `#icon-${name}`
    }
  }
}
</script>

