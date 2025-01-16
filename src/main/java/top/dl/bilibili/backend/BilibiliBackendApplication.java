package top.dl.bilibili.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "top.dl.bilibili.backend.mapper")
public class BilibiliBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BilibiliBackendApplication.class, args);
    }

}
