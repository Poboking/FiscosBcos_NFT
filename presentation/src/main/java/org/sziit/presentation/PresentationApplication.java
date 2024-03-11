package org.sziit.presentation;

import com.feiniaojin.gracefulresponse.EnableGracefulResponse;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableGracefulResponse
@MapperScan(basePackages = {"org.sziit.infrastructure.dao.mapper"})
@ComponentScan(basePackages = {"org.sziit.app", "org.sziit.infrastructure", "org.sziit.presentation"})
public class PresentationApplication {

    public static void main(String[] args) {
        SpringApplication.run(PresentationApplication.class, args);
    }

}
