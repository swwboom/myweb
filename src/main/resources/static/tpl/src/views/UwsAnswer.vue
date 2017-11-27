/*
 * @Author: LiuLiXue QQ:136890965 
 * @Date: 2017-10-24 20:21:08 
 * @Last Modified by: LiuLiXue
 * @Last Modified time: 2017-10-25 15:21:50
 */
//答案页面组件

<template>
  <div class="answer-wrap">
    <div class="img-wrap" v-if="isShowCoverImg">
      <img :src="coverImgSrc" alt="">
    </div>
    <uws-caption>
      <span v-text="caption"></span>
    </uws-caption>
    <uws-list v-text="question"/>
    <!-- answer -->
    <div class="caption">
      <uws-caption v-text="captionTo"/>
    </div>
    <div class="detail-wrap">
      <uws-normal-scroll :data="answer">
        <div class="wrap" v-html="answer"></div>
      </uws-normal-scroll>
    </div>
  </div>
</template>

<script>
  import UwsCaption from '@/components/caption/Caption'
  import UwsList from '@/components/list/NormalList'
  import UwsNormalScroll from '@/components/scroll/NormalScroll'
  import { getQuestion } from '@/api'
  import status from '@/api/status'
  export default {
    name: 'Answer',
    components: {
      UwsCaption,
      UwsList,
      UwsNormalScroll
    },
    data() {
      return {
        status: status.status,
        statusText: status.statusText,
        isShowCoverImg: false,//是否显示顶层图片
        coverImgSrc: '',
        caption: '问题描述',
        captionTo: '问题解答',
        question: '',
        imgList: [],
        answer: '',
        map: []
      }
    },
    created() {
      getQuestion(this.$route.params.id).then( response => {
        if(this.status == response.status && this.statusText === response.statusText){
          let data = response.data
          this.coverImgSrc = data.coverImageUrl
          this.question = data.question
          this.imgList = data.images
          this.answer = data.answer
          this.isShowCoverImg = !this.coverImgSrc ? false : true //用于判断是否显示顶层图片
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

<style lang="scss" scoped>
  @import '../style/mixin';
  @import '../style/_variables';
  $width: 10rem /* 750/75 */;
  $img-height: 2.64rem /* 198/75 */;
  .answer-wrap{
    width: $width;
  }
  .img-wrap{
    width: inherit;
    >img{
      width: inherit;
      height: $img-height;
    }
  }
  .caption{
    margin-top: .453333rem /* 34/75 */;
  }
  .detail-wrap{
    box-sizing: border-box;
    padding: .48rem /* 36/75 */ .346667rem /* 26/75 */;
    background-color: $common-bg-color;
    .wrap{
      box-sizing: border-box;
      padding: .133333rem /* 10/75 */ 0;
    }
  }
</style>