package com.qiao.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //项目中的所有/**支持跨域
                .allowedOriginPatterns("*")//所有地址都可以访问，也可以配置具体地址
                .allowCredentials(true)
                .allowedMethods("*")//"GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS"
                .maxAge(3600);// 跨域允许时间，以秒为单位，3600=60*60，即一小时
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());            //注册乐观锁插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));      //分页插件
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(JWTIntercept())
                .addPathPatterns("/AdminController/getAllAdmin","/AdminController/AdminCache");//拦截一个测试
    }

    @Bean
    public JWTIntercept JWTIntercept() {
        return new JWTIntercept();
    }

}
