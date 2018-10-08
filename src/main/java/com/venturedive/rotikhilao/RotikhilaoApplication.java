package com.venturedive.rotikhilao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
public class RotikhilaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RotikhilaoApplication.class, args);
    }


}
