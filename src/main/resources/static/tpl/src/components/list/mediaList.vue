/*
 * @Author: LiuLiXue QQ:136890965 
 * @Date: 2017-10-19 15:02:35 
 * @Last Modified by: LiuLiXue
 * @Last Modified time: 2017-10-25 16:17:37
 */
//列表组件

<template>
  <div class="item" :class="[isRowReserver ? reserve : normal]" >
    <div class="left-wrap">
      <div class="left-wrap__title">{{title}}</div>
      <div class="left-wrap__date">
        <div class="author">作者：{{author}}</div>
        <div class="date">{{stampToDate}}</div>
      </div>
    </div>
    <div class="right-wrap" v-if="isShowImg">
      <img :src="src">
    </div>
  </div>
</template>

<script>
export default {
  props: {
    isReserve: {
      type: String,
      default: 'right'
    },
    createTime: {},
    author: {
      type: String,
    },
    id: {
      type: Number
    },
    title: {
      type: String
    },
    src: {
      type: String
    }
  },
  data() {
    name:'MediaListItem'
    return{
      reserve: 'reserve',
      normal: 'normal'
    }
  },
  computed: {
    isRowReserver() {
      const res = this.$route.query.reserve
      return this.isReserve === 'left' || res === 'left' ? true : false 
    },
    isShowImg() {
      return !this.src ? false : true
    },
    stampToDate() {
      return this.uwsToDate(this.createTime)
    }
  },
}
</script>

<style lang="scss" scoped>
  @import '../../style/mixin';
  @import '../../style/_variables';
  $item-height: 2.16rem /* 162/75 */;
  $img-w-h: 1.466667rem /* 110/75 */;
  $border-color: rgba(163,163,163,1);
  $title-color: #4a4a4a;//标题颜色
  $date-color: #a3a3a3;//日期颜色
  %center{
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .item{
    width: 100%;
    height: $item-height;
    box-sizing: border-box;
    padding: 0 .346667rem /* 26/75 */;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    box-sizing: border-box;
    border-bottom: 1px solid $border-color;
    background-color: $common-bg-color;
    &.reserve{
      flex-direction: row-reverse;
      justify-content: flex-end;
      .right-wrap{
        margin-right: .986667rem /* 74/75 */;
      } 
    }
    &.normal{
      flex-direction: row;
      .right-wrap{
        margin-left: .986667rem /* 74/75 */;
      }
    }
    .left-wrap{
      flex: 1;
      // width: 6.826667rem /* 512/75 */;
      display: flex;
      flex-direction: column;
      justify-content: space-around;
      &__title{
        @include font-dpr(16px);
        @include line-height-dpr(20px);
        color: $title-color;
        overflow:hidden; 
        text-overflow:ellipsis;
        display:-webkit-box; 
        -webkit-box-orient:vertical;
        -webkit-line-clamp:2; 
      }
      &__date{
        @include font-dpr(12px);
        color: $date-color;
        margin-top: .2rem /* 15/75 */;
        display: flex;
        .author{
          margin-right: .133333rem /* 10/75 */;
        }
      }
    }
    .right-wrap{
      @extend %center;
      width: $img-w-h;
      height: $img-w-h;
      img{
        width: $img-w-h;
        height: $img-w-h;
        border-radius: 12px;
        border: 1px solid $border-color;
      }
    }
  }
</style>


