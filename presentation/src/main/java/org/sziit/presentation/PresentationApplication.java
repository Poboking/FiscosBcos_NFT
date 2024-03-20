package org.sziit.presentation;

import com.feiniaojin.gracefulresponse.EnableGracefulResponse;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Log4j2
@SpringBootApplication
@EnableGracefulResponse
@MapperScan(basePackages = {"org.sziit.infrastructure.dao.mapper", "org.sziit.infrastructure.repository"})
@ComponentScan(basePackages = {"org.sziit.app", "org.sziit.infrastructure", "org.sziit.presentation"})
public class PresentationApplication {

    public static void main(String[] args) {
        SpringApplication.run(PresentationApplication.class, args);
        log.info("[Knife4j文档地址]: http://localhost:8080/doc.html");
    }

}
