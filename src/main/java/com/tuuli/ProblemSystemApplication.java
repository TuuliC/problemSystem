package com.tuuli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ProblemSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProblemSystemApplication.class, args);
    }

}
