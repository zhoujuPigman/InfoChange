package cn.huanji.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger配置类
 * @author 猪肉佬
 * time 2020 03/18 14:48
 */
@Configuration
public class Swagger2Config {

    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.basePackage("cn.cgt.jjzw.assetsManagement.controller.scheduleManagement.controller"))
                .apis(RequestHandlerSelectors.basePackage("cn.huanji.Security.controller"))
                .paths(PathSelectors.any())
                .build();
/*                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());*/
    }

    @SuppressWarnings("deprecation")
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("标题：信息交换系统-登录认证模块接口文档")
                .description("描述：用于相关模块API接口文档")
                .contact("猪肉佬")
                .version("版本号:v1.0")
                .build();
    }
}
