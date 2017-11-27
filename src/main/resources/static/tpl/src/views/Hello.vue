/*
 * @Author: LiuLiXue QQ:136890965 
 * @Date: 2017-10-18 13:02:56 
 * @Last Modified by: LiuLiXue
 * @Last Modified time: 2017-10-26 16:48:41
 */
<template>
  <div class="hello-wrap">
    <img src="../assets/logo.png" alt="">
    <h1 class="hello-wrap__title" v-text="message"></h1>
    <h3 class="info">使用方式</h3>
    <div class="link">
      <ul>
        <li>
          <router-link :to="{path:'/uws-slider-tabs-list'}">/#/uws-slider-tabs-list?moduleId=参数[&reserve='left']</router-link>
          <div>
            <strong>说明:</strong>
            资讯类页面 moudleId必需,提供一个可选值reserve查询参数，默认页面图文列表为左标题右图片，如果设置reserve='left'则为左图右文
            注意：目前该文章列表接口tpl/listArticles/1/1/5未提供图片链接字符串所以不会有图片，后期需要图片的话字段名一定为src
          </div>
          <div><strong>例:</strong> /uws-slider-tabs-list?moduleId=1&reserve=left</div>
        </li>
        <li>
          <router-link :to="{path: '/uws-content-detail/1'}">/#/uws-content-detail/articleId</router-link>
          <div>
            <strong>说明:</strong>
            目前不支持video
            内容详情页面 此页面不单独使用是资讯类的二级页面，默认有两种展示方式
            如果/tpl/article/id 接口内article对象内author或title为空则不显示标题和时间
          </div>
        </li>
        <li>
          <router-link :to="{path:'/question-slider-caption-list'}">/#/question-slider-caption-list?moduleId=Number</router-link>
          <div><strong>说明:</strong>问题类页面moduleId必需通用如果需要一张图片bannerList数组包含一个对象,r如果不需要图片bannerList为[]</div>
        </li>
        <li>
          <router-link :to="{path:'/uws-answer/1'}">/#/answer</router-link>
          <div><strong>说明:</strong>答案页面 此页面一般不单独使用是问题类页面的二级页面</div>
        </li>
        <li>
          <router-link :to="{path:'/uws-slide-leftTab-rightList'}">/#/uws-slide-leftTab-rightList</router-link>
          <div><strong>说明:</strong>竖行tab页面</div>          
        </li>
        <li>
          <router-link :to="{path:'/uws-leftTab-rightslideList'}">/#/uws-leftTab-rightslideList</router-link>
          <div><strong>说明:</strong>竖行tab页面</div>   
        </li>
      </ul>
    </div>
    <div class="copyright-wrap" v-text="copyright"></div>
  </div>
</template>

<script>
export default {
  name: 'Hello',
  data() {
    return {
      message: '欢迎使用汇天科技app通用模板',
      copyright: 'Created By HiteamTech Web Team'
    }
  }
}
</script>

<style lang="scss" scoped>
  @import '../style/mixin.scss';
  ul,li{
    list-style: circle;
  }
  .hello-wrap{
    height: inherit;
    display: flex;
    flex-direction: column;
    // justify-content: center;
    align-items: center;
    >img{
      margin-top: 1.2rem /* 90/75 */;
    }
    &__title{
      @include font-dpr(16px);
      margin: .666667rem 0 1rem /* 300/75 *//* 50/75 */;
    }
    .copyright-wrap{
      width: 100%;
      position: fixed;
      bottom: 0;
      right: 0;
      z-index: 100;
      height: 1.333333rem /* 100/75 */;
      line-height: 1.333333rem /* 100/75 */;
      text-align: center;
      color: #c3c3c3;
      @include font-dpr(12px);
    }
  }
  .info{
    color: #666;
    margin-bottom: .133333rem /* 10/75 */;
  }
  .link{
    width: 100%;
    display: flex;
    justify-content: center;
    ul{
      padding-left: .4rem /* 30/75 */;
    }
    li{
      margin-bottom: .266667rem /* 20/75 */;
      div{
        margin-top: .133333rem /* 10/75 */
      }
    }
    a{
      color: #282B8D;
      @include font-dpr(14px);
    }
  }
</style>


