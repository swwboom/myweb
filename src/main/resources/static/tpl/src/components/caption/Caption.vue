/*
 * @Author: LiuLiXue QQ:136890965 
 * @Date: 2017-10-24 12:35:22 
 * @Last Modified by: LiuLiXue
 * @Last Modified time: 2017-10-24 16:23:04
 */
/**标题组件
 *接收一个iconClass属性可指定标题前面的icon
 *不传的话不显示
 */

<template>
  <div class="caption-wrap">
    <div class="icon-wrap" v-if="isShowIcon">
      <uws-icon :iconName="iconClass"/>
    </div>
    <slot>
    </slot> 
  </div>
</template>

<script>
  import UwsIcon from '../iconfont'
  export default {
    name: 'Caption',
    components: {
      UwsIcon
    },
    props: {
      caption: {
        type: String,
        default: '常见问题'
      },
      iconClass: {
        type: String
      }
    },
    computed: {
      isShowIcon() {
        return  this.iconClass ? true : false
      }
    }
  }
</script>

<style lang="scss" scoped>
  @import '../../style/mixin';
  @import '../../style/_variables';
  .icon-wrap{
    margin-right: .186667rem /* 14/75 */;
  }
  .caption-wrap{
    @include font-dpr($caption-font-size);
    color: $caption-color;
    height: $caption-height;
    display: flex;
    justify-content: flex-start;
    align-items: center;
    padding: 0 $caption-padding-lr;
    border-bottom: .013333rem /* 1/75 */ solid $caption-border-color;
    background-color: $common-bg-color;
  }
</style>


