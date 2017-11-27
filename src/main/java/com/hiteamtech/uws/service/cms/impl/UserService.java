package com.hiteamtech.uws.service.cms.impl;


import com.hiteamtech.uws.resultmapping.cms.LoginInfo;
import com.hiteamtech.uws.service.cms.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Override
    public LoginInfo login(String userName, String password) {

        if(userName.equals("admin") && password.equals("admin")){
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setUserId(1);
            loginInfo.setUserName("service@hiteamtech.com");
            loginInfo.setOrganizationName("汇天科技有限公司");
            return loginInfo;
        }
        return null;
    }
}
