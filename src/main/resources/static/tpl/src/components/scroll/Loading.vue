/*
 * @Author: LiuLiXue QQ:136890965 
 * @Date: 2017-10-21 21:03:25 
 * @Last Modified by: LiuLiXue
 * @Last Modified time: 2017-10-23 14:40:13
 */

<template>
  <div class="uws-loading-container">
    <img src="./loading.gif">
  </div>
</template>
<script>
  export default {
    name: 'Loading'
  }
</script>
<style lang="scss">
  .uws-loading-container{
    img{
      width: .4rem /* 30/75 */;
      height: .4rem /* 30/75 */;
      display: block;
    }
  }
    
</style>