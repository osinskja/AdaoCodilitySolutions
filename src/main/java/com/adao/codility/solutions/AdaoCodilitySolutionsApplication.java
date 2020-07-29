package com.adao.codility.solutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"controller", "repository", "config"})
@EntityScan("data")
public class AdaoCodilitySolutionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdaoCodilitySolutionsApplication.class, args);
    }

}
