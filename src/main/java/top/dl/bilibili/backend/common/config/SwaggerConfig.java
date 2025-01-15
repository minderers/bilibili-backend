package top.dl.bilibili.backend.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("中医在线 API")
                        .contact(new Contact().name("ctynt").email("2597704619@qq.com"))
                        .version("1.0")
                        .description("中医在线应用 API 接⼝⽂档")
                        .license(new License().name("Apache 2.0").url("http://doc.xiaominfo.com")));
    }
}
