package com.example.spring_jobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringJobsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJobsApplication.class, args);
    }

}
