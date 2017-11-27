package com.hiteamtech.uws.service.cms;

import com.hiteamtech.uws.resultmapping.cms.LoginInfo;


/**
 * Created by BigK on 2017/9/21.
 */
public interface IUserService {

    /**
     * 用户登录
     * @param userName
     *          用户名
     * @param password
     *          密码
     * @return
     *          用户不存在    响应 HttpCode  404
     *          密码错误
     *          登录成功      响应 HttpCode  200
     *
     */
    LoginInfo login(String userName, String password);


}
