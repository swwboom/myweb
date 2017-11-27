package com.hiteamtech.uws;


import com.didispace.swagger.EnableSwagger2Doc;
import com.hiteamtech.uws.filter.HTTPBasicAuthorizeAttribute;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@EnableSwagger2Doc
@SpringBootApplication
public class UwsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UwsServiceApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new HTTPBasicAuthorizeAttribute());
		//配置过滤器指定拦截的URI，不配置默认拦截所有
//		List<String> urlPatterns = new ArrayList<>();
//		urlPatterns.add("/cms/*");
//		urlPatterns.add("/tpl/*");
//		urlPatterns.add("/swagger-ui.html/*");
//		urlPatterns.add("/druid/*");
//		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}
}
