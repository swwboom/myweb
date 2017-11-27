/*
 * @Author: LiuLiXue QQ:136890965 
 * @Date: 2017-10-21 12:39:08 
 * @Last Modified by: LiuLiXue
 * @Last Modified time: 2017-10-21 17:52:54
 */

<template>
  <li class="list">
    <slot></slot>
  </li>
</template>

<script>
  export default {
    name: 'TabsItem',
    mounted(){
      this.$nextTick(()=>{
          this.$parent.initTab()
      })
    }
  }
</script>

<style lang="scss" scoped>
  .list{
    height: inherit;
    position: relative;
    display: inline-block;
    box-sizing: border-box;
    display: flex;
    div,a,p,span{
      flex: 1;
    }
  }
</style>


