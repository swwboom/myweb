package com.hiteamtech.uws.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Created on 2017/10/25.
 */
@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("Spring 容器启动完成，开始初始化配置数据...");
        //配置过滤器要放行的请求
        Config.patterns.add(Pattern.compile("/tpl/.*"));
        Config.patterns.add(Pattern.compile(".*/console/login.html"));
        Config.patterns.add(Pattern.compile(".*/user/login"));
        Config.patterns.add(Pattern.compile(".*/user/login"));

        Config.patterns.add(Pattern.compile(".*favicon.ico"));

        log.info("Spring 容器初始化配置数据完成");
    }
}
