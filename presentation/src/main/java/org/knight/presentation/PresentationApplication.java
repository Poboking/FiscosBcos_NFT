package org.knight.presentation;

import com.feiniaojin.gracefulresponse.EnableGracefulResponse;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Log4j2
@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties
@EnableScheduling
@EnableGracefulResponse
@MapperScan(basePackages = {"org.knight.infrastructure.dao.mapper", "org.knight.infrastructure.repository"})
@ComponentScan(basePackages = {"org.knight.app", "org.knight.infrastructure", "org.knight.presentation"})
public class PresentationApplication {

    public static void main(String[] args) {
        SpringApplication.run(PresentationApplication.class, args);
        log.info("[Knife4j文档地址]: http://localhost:8080/doc.html");
    }

}
