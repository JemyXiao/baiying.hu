package com.baiying.hu.common.intercrptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by jmx
 * on 2017/7/24
 */
@Configuration
@Slf4j
public class ValidInterceptor extends WebMvcConfigurerAdapter {

    @Value("${upload.path}")
    private String path;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
    }

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(testHandlerInterceptor())
                //添加需要验证登录用户操作权限的请求
                .addPathPatterns("/user/selectUserInfo", "/problem/createProblem");
        registry.addInterceptor(adminHandlerInterceptor())
                .addPathPatterns("/admin/*")
                .excludePathPatterns("/admin/login","/admin/fileUpload","/admin/multiUpload");
//                //排除不需要验证登录用户操作权限的请求
//                .excludePathPatterns("/user/login", "/user/register");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        String path = null;
//        try {
//            path = ResourceUtils.getFile(System.getProperty("user.dir") + "/upload/").getAbsolutePath();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        registry.addResourceHandler("/upload/**").addResourceLocations("file:/root/davince/upload/", "file:/root/davince/upload/");
        registry.addResourceHandler("/breviary/**").addResourceLocations("file:/root/davince/upload/", "file:/root/davince/breviary/");

        super.addResourceHandlers(registry);
    }

    @Bean
    public TestHandlerInterceptor testHandlerInterceptor() {
        return new TestHandlerInterceptor();
    }

    @Bean
    public AdminHandlerInterceptor adminHandlerInterceptor() {
        return new AdminHandlerInterceptor();
    }

}
