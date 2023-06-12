package com.qiao.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author AsteroidQiao
 * @Create 2023-02-18
 */
@Configuration
@EnableOpenApi//会自动开启配置，启动类不需要加任何注解
@EnableSwaggerBootstrapUI//访问美化，方便查看调试
public class Swagger3Config {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo()) //应用文档基本信息
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qiao.controller")) // swagger扫描路径
                .paths(PathSelectors.any()) // 应用于包下所有路径
                .build(); // 设置全局通用参数
    }


    /**
     * 文档基本信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger口文档")
                .description("swagger接口文档")
                .termsOfServiceUrl("https://asteroidqiao.top")
                .contact(new Contact("乔羽","https://asteroidqiao.top","asteroidqiao@163.com"))
                .version("2.0")
                .build();
    }

}
