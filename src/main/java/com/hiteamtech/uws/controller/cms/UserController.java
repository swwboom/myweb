package com.hiteamtech.uws.controller.cms;

import com.hiteamtech.uws.commons.Config;
import com.hiteamtech.uws.entity.UserLogin;
import com.hiteamtech.uws.kit.StrKit;
import com.hiteamtech.uws.resultmapping.cms.LoginInfo;
import com.hiteamtech.uws.service.cms.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2017/9/21.
 */
@Api(tags = "CMS-用户相关API文档")
@RestController
@RequestMapping("/cms/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "用户登录", notes = "用户登录接口")
    @ApiResponses({@ApiResponse(code = 200, message = "登录成功,系统自动跳转到指定页面"),
            @ApiResponse(code = 400, message = "请求错误，有未输入内容"),
            @ApiResponse(code = 401, message = "（未授权） 用户名或密码错误"),})
    @PostMapping(value = "/login")
    public ResponseEntity<Map> login(@RequestBody UserLogin userInfo, HttpServletResponse response, HttpServletRequest request, HttpSession httpSession) {
        String userName = userInfo.getUserName(), password = userInfo.getPassword(), loginCode = userInfo.getLoginCode();
        log.debug("用户登录，userName:" + userName + ",pwd:" + password);
        Map map = new HashMap();
        if (StrKit.isBlank(userName) || StrKit.isBlank(password) || StrKit.isBlank(loginCode)) {
            response.setStatus(400);
            map.put("msg", "请输入登录信息");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

        /*Object userToken = request.getSession().getAttribute(Config.USER_SESSION_KEY);
        if (userToken != null) {
            log.info("用户已经登录");
            // http code（成功）服务器已成功处理了请求
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        }*/


        LoginInfo loginInfo = userService.login(userName, password);
        if (loginInfo == null) {
            log.info("用户名，未查到对应的用户信息");
            map.put("msg", "用户名或密码错误");
            return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
            //（未授权） 请求要求身份验证。 对于需要登录的网页
//            return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
        } else {
        /* httpSession.setAttribute(Config.USER_SESSION_KEY, userSessionInfo.get("userId"));*/
            log.debug("用户：" + loginInfo.getUserName() + "登录成功，跳转至控制台页面...");
            request.getSession().setAttribute(Config.USER_SESSION_KEY, loginInfo);
            map.put("userId", loginInfo.getUserId());
            map.put("userName", loginInfo.getUserName());
            map.put("organizationName", loginInfo.getOrganizationName());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }

    }


}
