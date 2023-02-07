package com.tuuli;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootTest
class ProblemSystemApplicationTests {


    @Test
    void contextLoads() {
        try {
            File staticDir = new File(ResourceUtils.getURL("classpath:static").getPath());
            System.out.println(staticDir.getPath() + "\\questionsImages\\");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
