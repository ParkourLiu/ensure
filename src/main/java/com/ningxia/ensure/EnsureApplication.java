package com.ningxia.ensure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement // 开启事物
@SpringBootApplication
public class EnsureApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnsureApplication.class, args);
    }

}
