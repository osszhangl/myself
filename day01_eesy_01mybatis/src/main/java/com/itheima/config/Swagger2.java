package com.itheima.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * swagger配置类，指定需要扫描的包和设置文档的显示格式
 * Created by zhanglin on 2021/7/22.
 */
@Configuration
public class Swagger2 {


    public Docket creatRestAip(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.itheime.controller"))
                .paths(PathSelectors.any())
                .build();

    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("用户管理接口API文档")
                .version("1.0.0")
                .build();
    }

}
