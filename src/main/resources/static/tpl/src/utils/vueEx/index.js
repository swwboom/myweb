/*
 * @Author: LiuLiXue QQ:136890965 
 * @Date: 2017-10-20 22:56:48 
 * @Last Modified by: LiuLiXue
 * @Last Modified time: 2017-10-25 16:18:36
 */
 import Vue from 'vue'
 
Vue.prototype.uwsFlag = i => {
  return i === "false" ? false : i === false ? false : i === "true" || true ? true :true
}

//查询问题答案或者详情里面的标签插入图片
Vue.prototype.insertImg = (list,imageList) => {
  let [mLen,iLen] = [list.length,imageList.length]
  if(0 !== mLen && 0!==iLen){
    let len = mLen >= iLen ? iLen : mLen
    for(let i = 0; i < len ;i++){
      list[i].src = imageList[i].url
      list[i].style.width = '100%'
      list[i].style.height = '200px'
    }
  }else{
    return
  }
}

//时间戳转为日期
Vue.prototype.uwsToDate = stamp => {
  return new Date(stamp).toLocaleDateString()
}
