package org.usman.api.college;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.usman.api.college"})
public class UsmanSBCollegeApi {

    public static void main(String[] args) {
        SpringApplication.run(UsmanSBCollegeApi.class, args);
    }

}
