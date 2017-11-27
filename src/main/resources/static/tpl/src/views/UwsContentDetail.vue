/*
 * @Author: LiuLiXue QQ:136890965 
 * @Date: 2017-10-25 14:10:18 
 * @Last Modified by: LiuLiXue
 * @Last Modified time: 2017-10-25 15:32:59
 */
//内容详情页面级组件

<template>
  <div class="wrap">
    <uws-caption v-if="isShowCaption">
      <div v-text="caption"></div>
    </uws-caption>
    <div class="date-author-wrap" v-if="isShowDate">
      <uws-normal-list>
        <div class="date-author">
          <div class="author">作者：{{author}}</div>
          <div class="date">日期：{{stampToDate}}</div>
        </div>
      </uws-normal-list>
    </div>
    <div class="content-wrap">
      <uws-normal-scroll :data="content">
        <div class="detail-wrap" v-html="content"></div>
      </uws-normal-scroll>
    </div>
  </div>
</template>

<script>
  import UwsCaption from '@/components/caption/Caption'
  import UwsNormalList from '@/components/list/NormalList'
  import UwsNormalScroll from '@/components/scroll/NormalScroll'
  import { getArticleDetail } from '@/api'
  import status from '@/api/status'
  export default {
    name: 'UwsContentDetail',
    components: {
      UwsCaption,
      UwsNormalList,
      UwsNormalScroll
    },
    data() {
      return {
        status: status.status,
        statusText: status.statusText,
        isShowCaption: false,
        isShowDate: false,
        caption: '',
        content: '',
        author: '',
        date: '',
        imgList: [],
        videos: []
      }
    },
    computed: {
      stampToDate() {
        return this.uwsToDate(this.date)
      }
    },
    created() {
      getArticleDetail(this.$route.params.id).then( response => {
        if(this.status == response.status && this.statusText === response.statusText){
          let data = response.data
          this.content = data.article.content
          this.author = data.article.author
          this.date = data.article.createTime
          this.caption = data.article.title
          this.imgList = data.images
          this.video = data.videos
          this.isShowCaption = !this.caption ? false : true
          this.isShowDate = !this.date || !this.author ? false : true
        }
      })
    },
    mounted() {
      this.map = document.getElementsByClassName('articleImg');
    },
    updated() {
      this.insertImg(this.map,this.imgList)
    }
  }
</script>

<style lang='scss' scoped>
  @import '../style/_variables';
  $date-author-color: #9e9e9e;
  .wrap{
    .date-author-wrap{
      margin-bottom: .48rem /* 36/75 */;
    }
    .date-author{
      display: flex;
      justify-content: flex-start;
      color: $date-author-color;
      .author{
        margin-right: .266667rem /* 20/75 */;
      }
    }
    .content-wrap{
      .detail-wrap{
        box-sizing: border-box;
        padding: .48rem /* 36/75 */ .346667rem /* 26/75 */;
        background-color: $common-bg-color;
        .wrap{
          box-sizing: border-box;
          padding: .133333rem /* 10/75 */ 0;
        }
      }
    }
  }
</style>
