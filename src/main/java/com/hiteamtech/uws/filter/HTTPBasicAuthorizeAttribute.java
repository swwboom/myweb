package com.hiteamtech.uws.filter;

import com.hiteamtech.uws.commons.Config;
import com.hiteamtech.uws.resultmapping.cms.LoginInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by BigK on 2017/10/10.
 */
public class HTTPBasicAuthorizeAttribute implements Filter {

    private final static Logger log = LoggerFactory.getLogger(HTTPBasicAuthorizeAttribute.class);

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String uri = httpRequest.getRequestURI();
        if (isInclude(uri)){
            chain.doFilter(request, response);
            log.debug("拦截到放行请求,URI:"+uri+"---->URL:"+httpRequest.getRequestURL());
            return;
        } else {
            LoginInfo loginInfo = (LoginInfo) httpRequest.getSession().getAttribute(Config.USER_SESSION_KEY);

            if (loginInfo != null){
                // session存在
                log.info("userName:"+loginInfo.getUserName());
                chain.doFilter(request, response);
                return;
            } else {
                // session不存在 准备跳转失败
                log.debug("拦截到非法请求,URI:"+uri+"---->URL:"+httpRequest.getRequestURL()+"ContextPath()"+httpRequest.getContextPath());
                httpResponse.setHeader("Cache-Control", "no-store");
                httpResponse.setDateHeader("Expires", 0);
                httpResponse.setHeader("Prama", "no-cache");
                //此处设置了访问静态资源类
                httpResponse.sendRedirect("http://"+httpRequest.getServerName()+"/console/login.html");

            }
        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    /**
     * 是否需要过滤
     * @param url
     * @return
     */
    private static boolean isInclude(String url) {
        try {
            for (Pattern pattern : Config.patterns) {
                Matcher matcher = pattern.matcher(url);
                if (matcher.matches()) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("正则匹配异常，uri:"+url);
        }
        return false;
    }



}
